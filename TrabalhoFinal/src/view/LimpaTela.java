package view;

public class LimpaTela {
    public static void limpar(){
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
