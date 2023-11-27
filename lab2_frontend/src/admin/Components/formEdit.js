import React, { useState, useEffect} from "react";

/*Components*/
import {
  Button,
  Form,
  Input,
  Row,
  Col,
  Modal,
  message
} from "antd";
import axios from "axios";

function FormEdit({visible, setVisible, data, handleChangeIsClickBtn, isClickBtn}) {
  const [form] = Form.useForm();
  const phoneRegex = /([+84|84|0]+(3|5|7|8|9|1[2|6|8|9]))+([0-9]{8})\b/;

  const [visibleModal, setVisibleModal] = useState(false);
  useEffect(() => {
    setVisibleModal(visible);
  }, [visible]);

  const handleCancel = () => {
    form.resetFields();
    setVisibleModal(false);
    if (typeof setVisible === "function") {
      setVisible(false);
    }
  };
  useEffect(() => {
    if (!!data) {
      onInitData();
    }
  });

  const onInitData = () => {
    const { id, fullName, email, phone, address } = data;
    form.setFieldsValue({
      id,
      fullName,
      email,
      phone,
      address,
    });
  };

  const onFinish = async (values) => {
    try {
      const {id} = data;
      const res = await axios.put(`http://localhost:8080/api/employees/${id}`,values);
      if (!!res) {
        message.success("Cập nhật thành công!");
        setVisible(false);
        handleChangeIsClickBtn(!isClickBtn)
      }
    } catch (e) {
      console.log("Error", e);
      message.error(e.message);
    } finally {
    }
  };


  return (
    <Modal
    title="Cập Nhật Thông Tin Nhân Viên"
    open={visibleModal}
    onOk={() => handleCancel()}
    onCancel={() => handleCancel()}
    width="50%"
    footer={null}
>
          <Form
            form={form}
            onFinish={onFinish}
            name="form_add_account"
            className="ant-advanced-search-form"
          >
            <Row gutter={15}>
              <Col lg={12} xs={24}>
                <Form.Item
                  label="Họ và tên"
                  name="fullName"
                  rules={[
                    { required: true, message: "Vui lòng nhập tên nhân viên" },
                  ]}
                >
                  <Input maxLength={100}/>
                </Form.Item>
                <Form.Item
                  label="Email"
                  name={"email"}
                  rules={[
                    { required: true, message: "Vui lòng nhập email" },
                    { type: "email", message: "Email không đúng định dạng!"}
                  ]}
                >
                  <Input />
                </Form.Item>
              </Col>
              <Col lg={12} xs={24}>
                <Form.Item
                  label="Số điện thoại"
                  name="phone"
                  rules={[
                    { required: true, message: "Vui lòng nhập số điện thoại" },
                    { pattern: new RegExp(phoneRegex), message: "Số điện thoại không đúng định dạng!"}
                  ]}
                >
                  <Input />
                </Form.Item>
                <Form.Item
                  label="Địa chỉ"
                  rules={[{ required: true, message:"Vui lòng nhập địa chỉ"}]}

                  name="address"
                >
                  <Input maxLength={150}/>
                </Form.Item>
              </Col>
            </Row>
            <div>
              <Button
                className="btn-signin"
                type="light"
                size="large"
                onClick={handleCancel}
              >
                Hủy
              </Button>
              <Button
                className="btn-signin"
                htmlType="submit"
                type="primary"
                size="large"
              >
                Lưu
              </Button>
            </div>
          </Form>
    </Modal>
  );
}

export default FormEdit;
