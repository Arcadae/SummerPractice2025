import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;

public class BankAccount {
    //Имя владельца
    private String nameOfHolder;
    //Баланс аккаунта
    private int accountBalance;
    //Дата открытия
    private LocalDateTime registrationDate;
    //Заблокирован ли счёт
    private boolean blockingFlag;
    //Номер счёта аккаунта
    private String number;

    /*Блок инициализации значений атрибутов по умолчанию*/
    {
        accountBalance = 0;
        registrationDate = LocalDateTime.now();
        number = new Random().ints(8,0,9)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining());
    }
    /*Конец блока*/

    //Конструктор класса
    public BankAccount (String nameOfHolder, boolean isBlocked) {
        this.nameOfHolder = nameOfHolder;
        blockingFlag = isBlocked;
    }

    //Метод для просмотра данных
    @Override
    public String toString() {
        //Форматирование даты открытия в строковой тип
        String formattedRegistrationDate = registrationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //Вывод данных об аккаунте
        return "Аккаунт{" +
                "Имя владельца: " + nameOfHolder +
                ", Баланс аккаунта = " + accountBalance + 
                ", Дата регистрации: " + formattedRegistrationDate + 
                ", Заблокирован ли: " + blockingFlag + 
                ", Номер аккаунта: " + number + 
                "}";
    }

    //Метод для пополнения баланса счёта
    public boolean deposit(int amount) {
        if ((blockingFlag != true) && (amount > 0)) {
            accountBalance += amount;
            System.out.println(
                String.format("%s! Значение вашего баланса было пополнено на сумму равную %d. Теперь ваш баланс: %d",
                    nameOfHolder,
                    amount,
                    accountBalance)
                );
            return true;
        }
        System.out.println("Операция невозможна");
        return false;
    }

    //Метод для снятия средств со счёта
    public boolean withdraw(int amount) {
        if ((blockingFlag != true) && (amount > 0) && (amount <= accountBalance)) {
            accountBalance -= amount;
            System.out.println(
                String.format("%s! С вашего баланса было списано значение на сумму равную %d. Теперь ваш баланс: %d",
                    nameOfHolder,
                    amount,
                    accountBalance)
                );
            return true;
        }
        System.out.println("Операция невозможна");
        return false;
    }

    //Метод для перевода средств на другой счёт
    public boolean transfer(BankAccount otherAccount, int amount) {
        if((blockingFlag != true && otherAccount.isBlocked() != true) && (amount > 0) && (amount < accountBalance)) {
            accountBalance -= amount;
            otherAccount.accountBalance += amount;
            System.out.println(
                String.format("%s! С вашего баланса на баланс пользователя %s было переведено значение на сумму равную %d. Теперь ваш баланс: %d",
                    nameOfHolder, 
                    otherAccount.nameOfHolder,
                    amount ,
                    accountBalance)
                );
            return true;
        }
        System.out.println("Операция невозможна");
        return false;
    }

    private boolean isBlocked() {
        return blockingFlag;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        BankAccount account = (BankAccount) o;

        if(nameOfHolder != account.nameOfHolder) return false;
        if(accountBalance != account.accountBalance) return false;
        if(registrationDate != account.registrationDate) return false;
        if(blockingFlag != account.blockingFlag) return false;
        return number.equals(account.number);

    }

    @Override
    public int hashCode() {
        int result = accountBalance;
        result = 31 * result + (nameOfHolder.hashCode());
        result = 31 * result + (registrationDate.hashCode());
        result = 31 * result + Boolean.hashCode(blockingFlag);
        result = 31 * result + (number.hashCode());
        return result;
    }
}