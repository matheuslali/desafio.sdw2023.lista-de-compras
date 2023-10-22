package me.dio.domain.model;

import jakarta.persistence.*;



@Entity(name = "tb_shoppingList")
public class ShoppingList {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private User user;
   private String productName;
   private Integer quantity;

   public User getUser() {
      return user;
   }

   public Long getId() {
      return id;   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }
}
