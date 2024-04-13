public class Info {
    public static void main(String[] args) {
        // Thông tin cá nhân
        String name = "Phan Hoang Dung";
        String msv = "23020346";
        String classroom = "K68_AI2";
        String github = "DungPhanHoangg05";
        String email = "dung.phan.hoang05@gmail.com";

        System.out.println(name + "\t" + msv + "\t" + classroom + "\t" + github + "\t" + email);

        int bottles = 9;

        while (bottles > 0) {
            System.out.println(bottles + " bottles of beer on the wall, " + bottles + " bottles of beer.");
            System.out.println("Take one down, pass it around,");
            bottles--;

            if (bottles == 1) {
                System.out.println(bottles + " bottle of beer on the wall, " + bottles + " bottle of beer.");
                System.out.println("Take one down, pass it around,");
                break;
            }
        }
        System.out.println("No more bottles of beer on the wall.");
    }
}
