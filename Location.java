public class Location {
    private String country = "none";
    private String city;
    private String address;
    private int room = -1; //rooms are numbered from 0

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location(){};


    public Location (String country, String city, String address, int room){
        this.country = country;
        this.city = city;
        this.address = address;
        this.room = room;
    }

    public String toString(){
        if (country.equals("none") && room == -1) {
            if (address.equals("none")) return city;
            else return address + ", " + city;
        }
        else if (country.equals("none"))
            return address + ", " + city + " - room " + room;
        else if (room == -1) {
            if (address.equals("none")) return city + ", " + country;
            return address + ", " + city + ", " + country;
        }
        return address + ", " + city + ", " + country + " - room " + room;
    }
}
