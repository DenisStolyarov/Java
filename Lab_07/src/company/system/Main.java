package company.system;
import company.system.DAO.Entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        var DTO = new SouvenirDAO();

        Show(DTO.selectAll());

        var souvenir = new Souvenir(10);
        var manufacter = new Manufacture();

        souvenir.setName("Test");
        souvenir.setPrice((float) 14.14);
        souvenir.setProdactionDate(new Date());
        manufacter.setName("ALENKA");
        manufacter.setCountry("RUSSIA");
        souvenir.setManufacture(manufacter);

        DTO.insert(souvenir);
        Show(DTO.selectAll());

        var a= new ArrayList<Souvenir>((Collection<? extends Souvenir>) DTO.selectAll());
        var id = a.stream().reduce((first, last)->last).map(Souvenir::getId).orElse(1);

        souvenir = DTO.select(id);
        System.out.println("Selected->"+souvenir+"\n------------");

        souvenir.setName("Update");
        DTO.update(souvenir);
        Show(DTO.selectAll());

        DTO.delete(id);
        Show(DTO.selectAll());

        DTO.Close();
    }

    public static void Show(Iterable<Souvenir> souvenirs){
        for (var i: souvenirs) {
            System.out.println(i);
        }
        System.out.println("--------------------------------------");
    }
}
