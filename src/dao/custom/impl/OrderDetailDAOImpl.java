package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;
import entity.OrderDetailPK;

public class OrderDetailDAOImpl implements OrderDetailDAO {

  @Override
  public void setSession(Session session) {

  }

  @Override
  public List<OrderDetail> findAll() throws Exception {
    ResultSet rst = CrudUtil.execute("SELECT * FROM `OrderDetail`");
    List<OrderDetail> orderDetails = new ArrayList<>();
    while (rst.next()) {
      orderDetails.add(new OrderDetail(rst.getString(1),
          rst.getString(2),
          rst.getInt(3),
          rst.getBigDecimal(4)));
    }
    return orderDetails;
  }

  @Override
  public OrderDetail find(OrderDetailPK key) throws Exception {
    ResultSet rst = CrudUtil.execute("SELECT * FROM `OrderDetail` WHERE orderId=? AND itemCode=?", key.getOrderId(), key.getItemCode());
    if (rst.next()) {
      return new OrderDetail(rst.getString(1),
          rst.getString(2),
          rst.getInt(3),
          rst.getBigDecimal(4));
    }
    return null;
  }

  @Override
  public void save(OrderDetail orderDetail) throws Exception {
    return CrudUtil.execute("INSERT INTO `OrderDetail` VALUES (?,?,?,?)", orderDetail.getOrderDetailPK().getOrderId(),
        orderDetail.getOrderDetailPK().getItemCode(), orderDetail.getQty(), orderDetail.getUnitPrice());
  }

  @Override
  public void update(OrderDetail orderDetail) throws Exception {
    return CrudUtil.execute("UPDATE OrderDetail SET qty=?, unitPrice=? WHERE orderId=? AND itemCode=?", orderDetail.getQty(), orderDetail.getUnitPrice(), orderDetail.getOrderDetailPK().getOrderId(),
        orderDetail.getOrderDetailPK().getItemCode());
  }

  @Override
  public void delete(OrderDetailPK orderDetailPK) throws Exception {
    return CrudUtil.execute("DELETE FROM `OrderDetail` WHERE orderId=? AND itemCode=?", orderDetailPK.getOrderId(), orderDetailPK.getItemCode());
  }
}
