package it.unitn.disi.entities;

public class Product {

    private int id;
    private String name;
    private String description;
    private int idSubcategory;

    private double averageReview;

    public double getAverageReview() {
        return averageReview;
    }

    private ShopProduct shopProduct; //negozio che vende al prezzo minimo
    private Image image; //immagine principale del prodotto
    private ReviewProduct[] review;

    public ReviewProduct[] getReview() {
        return review;
    }

    public void setReview(ReviewProduct[] review) {
        this.review = review;
        calculateAverageReview();
    }

    private void calculateAverageReview() { //meglio salvarlo
        double sommaReview = 0.0;
        if (review == null || review.length == 0) {
            averageReview = 0.0;
        } else {
            for (ReviewProduct r : review) {
                sommaReview += (double) r.getRate();
            }
            averageReview = sommaReview / (double) review.length;
        }
    }

    public Product(int id, String name, String description, int idSubcategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idSubcategory = idSubcategory;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the idSubcategory
     */
    public int getIdSubcategory() {
        return idSubcategory;
    }

    /**
     * @param idSubcategory the idSubcategory to set
     */
    public void setIdSubcategory(int idSubcategory) {
        this.idSubcategory = idSubcategory;
    }

    /**
     * @return the shopProduct
     */
    public ShopProduct getShopProduct() {
        return shopProduct;
    }

    /**
     * @param shopProduct the shopProduct to set
     */
    public void setShopProduct(ShopProduct shopProduct) {
        this.shopProduct = shopProduct;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
    // </editor-fold>

}
