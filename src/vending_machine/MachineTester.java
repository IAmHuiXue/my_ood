package vending_machine;

import java.util.HashMap;
import java.util.Map;

public class MachineTester {
    public static void main(String[] args) {
        Map<ItemType, Integer> items = new HashMap<>() {{
            put(ItemType.COKE, 6000);
            put(ItemType.CHIPS, 7000);
            put(ItemType.PROTEIN_BAR, 100000);
        }};
        VendingMachine vm = new VendingMachine(items);

        vm.pickItem(ItemType.CHIPS);
        vm.cashPay(80000);
        if (vm.hasChange()) {
            System.out.println(vm.giveChange());
        }
        System.out.println(vm.dispense());
        System.out.println(vm.getList());
    }
}
