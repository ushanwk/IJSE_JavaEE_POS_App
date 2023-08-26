package lk.ijse.dto;

public class ItemDTO {

    private String itemCode;
    private String itemName;
    private int itemQty;
    private int itemPrice;

    public ItemDTO(String itemCode, String itemName, int itemQty, int itemPrice) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setItemQty(itemQty);
        this.setItemPrice(itemPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
