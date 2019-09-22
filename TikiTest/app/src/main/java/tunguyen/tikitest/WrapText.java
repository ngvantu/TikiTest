package tunguyen.tikitest;

public class WrapText {

    public static String to2Lines(String origin) {
        String[] sub = origin.split(" ");
        if (sub.length == 1)
            return origin;

        StringBuilder part1 = new StringBuilder();
        StringBuilder part2 = new StringBuilder(origin);
        int diff = origin.length();

        for (int index = 0; index < sub.length; index++){
            if (part1.length() < part2.length()){
                StringBuilder temp1 = new StringBuilder(part1);
                StringBuilder temp2 = new StringBuilder(part2);
                if (index > 0){
                    temp1.append(' ');
                }
                temp1.append(sub[index]);
                temp2.delete(0, sub[index].length());
                if (index < sub.length - 1)
                    temp2.deleteCharAt(0);

                int newDiff = Math.abs(temp2.length() - temp1.length());

                if (newDiff >= diff) break;

                part1 = temp1;
                part2 = temp2;
                diff = newDiff;
            }
        }

        return part1.append("\n").append(part2).toString();
    }
}
