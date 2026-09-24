package parcial1;

public class Main {
    public static void main(String[] args) {
        // Crear la empresa DevPlus con sus datos básicos
        DevPlus empresa = new DevPlus(
                "DevPlus",
                "1094938205",
                "Barrio Los quindos M 15 casa 27",
                "3143415131",
                "www.devplus.com");

        // Crear el gestor general que coordina todo el sistema
        GestorGeneral gestorGeneral = new GestorGeneral(empresa);

        // Crear el menú y mostrar el menú principal
        Menu menu = new Menu(gestorGeneral);
        menu.mostrarMenuPrincipal();
    }
}