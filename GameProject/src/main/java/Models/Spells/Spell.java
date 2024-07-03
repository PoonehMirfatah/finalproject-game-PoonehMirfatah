package Models.Spells;

public abstract class Spell implements spell{
     String name;
     int price;

     public Spell(String name,int price){
         this.name=name;
         this.price=price;
     }
}
 interface spell {
    int getPrice();
    void drop();
}