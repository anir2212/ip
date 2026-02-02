public class Ui {

    public void showWelcome() {
        String logo = "    ___     ___   ___  _______\n"
                + "   /   \\   |   \\  | | |__  __|\n"
                + "  / /_\\ \\  | |\\ \\ | |   |  |\n"
                + " / ----- \\ | | \\ \\| | __|  |__\n"
                + "/__/   \\__\\| |  \\___| |______|";



        System.out.println("Hello from\n" + logo);



        String intro = "___________________________________\n" +
                "Hello! I'm Ani\n"
                + "What can I do for you?\n" + "________________________________";

        System.out.println(intro);

    }



    public void showExit() {
        String exit = "___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________";
        System.out.println(exit);
    }




}
