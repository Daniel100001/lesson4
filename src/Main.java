import java.util.Random;

public class Main {
    public static int bossHealth = 700;
//    Сделал их жизни проше чтобы считать было легче
    public static int bossDamage = 10;
    public static int[] heroHealth = {100, 100, 100, 100};
    public static int[] heroDamage = {10, 30, 30, 0};
    public static String[] heroAttackType = {"Logan", "Cyclops", "Wizard", "Medic"};
    public static String bossBarrier;

    public static void main(String[] args) {
        printStatistic();
        while (!isGameFinish()) {
            round();
        }
    }

    public static void regenTypeMedic() {
        int index = 0;
        int regen = 60;
        for (int i = 0; i < heroAttackType.length; i++) {
            if (heroHealth[i] < 100 && heroHealth[i] > 0){
                heroHealth[i] += regen;
                System.out.println("medic regen - " + heroAttackType[i]);
                break;
                }
            }
        }


    public static void bossTypeBarrier() {
        Random random = new Random();
        int index = random.nextInt(heroAttackType.length);
        bossBarrier = heroAttackType[index];
        for (int i = 0; i < heroAttackType.length; i++) {
            if (bossBarrier.equals(heroAttackType[i])) {
                System.out.println("Boss barrier equals " + bossBarrier);
                bossHealth += heroDamage[i];
            }
        }
    }

    public static void round() {
        bossHits();
        heroHits();
        bossTypeBarrier();
        regenTypeMedic();
        printStatistic();
    }

    public static boolean isGameFinish() {
        if (bossHealth <= 0) {
            System.out.println("HEROES WIN");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("BOSS WIN");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] > 0) {
                heroHealth[i] -= bossDamage;
                if (heroHealth[i] <= 0) {
                    heroHealth[i] = 0;
                }
            }
        }
    }

    public static void heroHits() {
        for (int hero : heroDamage) {
            if (bossHealth > 0) {
                if (hero > bossHealth) {
                    bossHealth = 0;
                } else {
                    bossHealth -= hero;
                }
            }
        }
    }

    public static void printStatistic() {
        System.out.println("Boss health " + bossHealth + ", " + " damage [" + bossDamage + "]");

        for (int i = 0; i < heroHealth.length; i++) {
            System.out.println("Hero" + " " + heroAttackType[i] + " " + "health" + " " + heroHealth[i] + " " + "damage [" + heroDamage[i] + "]");
        }
        System.out.println("************************************************** ");
    }
}