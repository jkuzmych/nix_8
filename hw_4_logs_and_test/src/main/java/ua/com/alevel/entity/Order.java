package ua.com.alevel.entity;

public class Order extends BaseEntity {
    private String name;
    private int amount;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + super.getId() + '\'' +
                ", text=" + name +
                ", amount=" + amount +
                ", user=" + userId +
                '}';
    }
}