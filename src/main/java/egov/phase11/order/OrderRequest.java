package egov.phase11.order;

public class OrderRequest {

    private final long itemId;
    private final int orderStock;

    public OrderRequest(long itemId, int orderStock) {
        this.itemId = itemId;
        this.orderStock = orderStock;
    }

    public long getItemId() {
        return itemId;
    }

    public int getOrderStock() {
        return orderStock;
    }
}
