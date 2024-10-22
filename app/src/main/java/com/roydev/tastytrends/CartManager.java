//package com.roydev.tastytrends;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartManager {
//    private List<ShopItem> cartItems = new ArrayList<>();
//
//    public void addItem(ShopItem item) {
//        // Check if item already exists in the cart
//        for (ShopItem cartItem : cartItems) {
//            if (cartItem.getId().equals(item.getId())) {
//                cartItem.setQuantity(cartItem.getQuantity() + 1);
//                return;
//            }
//        }
//        // If not found, add new item with quantity 1
//        item.setQuantity(1);
//        cartItems.add(item);
//    }
//
//    public void removeItem(String itemId) {
//        cartItems.removeIf(item -> item.getId().equals(itemId));
//    }
//
//    public void updateItemQuantity(String itemId, int quantity) {
//        for (ShopItem item : cartItems) {
//            if (item.getId().equals(itemId)) {
//                item.setQuantity(quantity);
//                return;
//            }
//        }
//    }
//
//    public List<ShopItem> getCartItems() {
//        return cartItems;
//    }
//}
//
