package com.service.implementations;

import com.DAO.ICategoryRep;
import com.DAO.IImageRep;
import com.DAO.ITagRep;
import com.entity.Category;
import com.entity.Image;
import com.entity.ImageFull;
import com.entity.Tag;
import com.exceptions.FileStorageException;
import com.exceptions.MyFileNotFoundException;
import com.payload.ImageUpdate;
import com.payload.ImageUpload;
import com.payload.ResizedImage;
import com.service.IImageService;
import com.util.ImageResizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.*;

@Service
public class ImageService implements IImageService {

    @Autowired
    private EntityManager em;
    @Autowired
    private IImageRep imageRep;
    @Autowired
    private ITagRep tagRepository;
    @Autowired
    private ICategoryRep categoryRep;

    public Image saveImage(ImageUpload imageUpload) {
        MultipartFile file = imageUpload.getFile();
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(imageName.contains("..")) {
                throw new MyFileNotFoundException("Filename contains invalid path sequence " + imageName);
            }
            String imageString = imageName.substring(0, imageName.lastIndexOf("."));

            ResizedImage resizedImage = ImageResizeUtil.resize(file.getBytes());

            ImageFull full = new ImageFull();
            full.setData(file.getBytes());
            Image image = new Image(imageString, file.getContentType(), file.getSize(),
                    resizedImage.getData(), imageUpload.getDescription(), resizedImage.getHeight(),
                    resizedImage.getWidth(), full, imageUpload.getCategories(), imageUpload.getTags());
            return imageRep.save(image);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
        }
    }
    public Image getImage(Long imageId) {
        return imageRep.findById(imageId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + imageId));
    }

    public List<Image> getAllImages() {
        return (List<Image>) imageRep.findAll();
    }

    public void deleteImage(Long imageId) {
        imageRep.deleteById(imageId);
    }

    public Image updateImage(ImageUpdate imageUpdate ) {
        Optional<Image> optionalImage = imageRep.findById(imageUpdate.getId());
        Image image = null;
        if (optionalImage != null){
            image = optionalImage.get();

            image.setCategories(imageUpdate.getCategories());
            image.setTags(imageUpdate.getTags());
            image.setName(imageUpdate.getName());
            image.setDescription(imageUpdate.getDescription());
            image.setDate(imageUpdate.getDate());
        }
        return  imageRep.save(image);
    };

    public List<Image> customFindByNameDes(String text) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(text!=null) {
            predicates.add(cb.or(
                    cb.like(root.get("name"), "%" + text + "%"),
                    cb.like(root.get("description"), "%" + text + "%"))
            );
        }
        return (List<Image>) cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public List<Image> getAllImagesBySearch(String searchString, Set<Long> tagsIds, Set<Long> categoriesIds) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);

        List<Image> imagesCollected = new ArrayList<>();

        Join<Image, Tag> tags = root.join("tags", JoinType.LEFT);
        Join<Image, Category> categories = root.join("categories", JoinType.LEFT);
//        Class<? extends Category> subclass = Image.class;
        cq.select(root);

        Predicate predicate = null;





        cq.where(predicate);

        List<Image> imagesWithoutDuplicates = new ArrayList<>(
                new HashSet<>(imagesCollected));
        return imagesWithoutDuplicates;
    }
}