public class App {
    public static void main(String[] args) throws Exception {
        
        BankAccount firstAccount = new BankAccount("Stas",false);
        BankAccount secondAccount = new BankAccount("Oleg",false);

        System.out.println(firstAccount.toString() + "\n");
        System.out.println(secondAccount.toString() + "\n");
        firstAccount.deposit(150);
        System.out.println(firstAccount.toString() + "\n");
        firstAccount.transfer(secondAccount, 50);
        System.out.println(firstAccount.toString() + "\n");
        System.out.println(secondAccount.toString() + "\n");

        System.out.println(firstAccount.hashCode());
        System.out.println(secondAccount.hashCode());

        System.out.println(firstAccount.equals(secondAccount));
        System.out.println(firstAccount.equals(firstAccount));
    }
}
