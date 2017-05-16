package leosin.leosinweather.bean;

import java.io.Serializable;

/**
 * Author: LeosinZhang
 * Time: 2017/5/16 11:13
 * Describle:
 */
public class LocationBean implements Serializable {
    /**
     * address : CN|四川|成都|None|CHINANET|0|0
     * content : {"address":"四川省成都市","address_detail":{"city":"成都市","city_code":75,"district":"","province":"四川省","street":"","street_number":""},"point":{"x":"104.06792346","y":"30.67994285"}}
     * status : 0
     */

    private String address;
    private ContentBean content;
    private int status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ContentBean {
        /**
         * address : 四川省成都市
         * address_detail : {"city":"成都市","city_code":75,"district":"","province":"四川省","street":"","street_number":""}
         * point : {"x":"104.06792346","y":"30.67994285"}
         */

        private String address;
        private AddressDetailBean address_detail;
        private PointBean point;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public AddressDetailBean getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(AddressDetailBean address_detail) {
            this.address_detail = address_detail;
        }

        public PointBean getPoint() {
            return point;
        }

        public void setPoint(PointBean point) {
            this.point = point;
        }

        public static class AddressDetailBean {
            /**
             * city : 成都市
             * city_code : 75
             * district :
             * province : 四川省
             * street :
             * street_number :
             */

            private String city;
            private int city_code;
            private String district;
            private String province;
            private String street;
            private String street_number;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }
        }

        public static class PointBean {
            /**
             * x : 104.06792346
             * y : 30.67994285
             */

            private String x;
            private String y;

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }
        }
    }
}
