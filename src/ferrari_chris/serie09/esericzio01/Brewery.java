package ferrari_chris.serie09.esericzio01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

abstract class Beer {
    private final double abv;
    private final String name;
    private final String origin;

    public Beer(String nome, String origin, double abv) {
        this.name = nome;
        this.origin = origin;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public double getAbv() {
        return abv;
    }

    @Override
    public String toString() {
        return String.format(" - name: %s, origin: %s, abv: %.1f%%", getName(), getOrigin(), getAbv());
    }
}

class Trappist extends Beer {
    public Trappist(String nome, double abv) {
        super(nome, "Belgium, Netherlands", abv);
    }

    @Override
    public String toString() {
        return "Trappist" + super.toString();
    }
}

class PaleAle extends Beer {
    public PaleAle(String nome, double abv) {
        super(nome, "England", abv);
    }

    @Override
    public String toString() {
        return "Pale Ale" + super.toString();
    }
}

class Weisse extends Beer {
    public Weisse(String nome, double abv) {
        super(nome, "Germany", abv);
    }

    @Override
    public String toString() {
        return "Weisse" + super.toString();
    }
}

public class Brewery {
    static void add(List<? extends Beer> source, Class<? extends Beer> beer, List<? super Beer> target){
        source.stream().filter(beer::isInstance).forEach(target::add);
    }
    static List<Beer> filter(List<? extends Beer> source, Class<? extends Beer> beer){
        return source.stream().filter(beer::isInstance).collect(Collectors.toList());
    }
    static <T extends Beer> List<T> filterGeneric(List<? extends Beer> source, Class<? extends Beer> beer){
        return (List<T>) source.stream().filter(beer::isInstance).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<Beer> beers = Arrays.asList(
                new Weisse("BRLO", 4),
                new Trappist("Rochefort 10", 11.3),
                new PaleAle("Brewdog dead pony", 3.8),
                new Trappist("Achel blonde", 8),
                new Weisse("Amundsden Lush", 5.3)
        );
        List<Beer> weisseBeer = new ArrayList<>();
        add(beers, Weisse.class, weisseBeer);
        List<Beer> trappists = filter(beers, Trappist.class);
        List<PaleAle> paleAles = filterGeneric(beers, PaleAle.class);
    }
}

