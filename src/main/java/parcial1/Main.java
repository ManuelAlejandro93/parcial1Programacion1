package parcial1;

public class Main {
    public static void main(String[] args) {
        // Crear la empresa DevPlus con sus datos básicos
        DevPlus empresa = new DevPlus(
                "DevPlus",
                "123456789",
                "Calle Principal 123, Bogotá",
                "601-2345678",
                "www.devplus.com");

        // Crear el gestor general que coordina todo el sistema
        GestorGeneral gestorGeneral = new GestorGeneral(empresa);

        // Crear el menú y mostrar el menú principal
        Menu menu = new Menu(gestorGeneral);
        menu.mostrarMenuPrincipal();
    }
}