package zbv5.cn.XiaoJoinItem.util;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.ConfigSection;

public class ItemUtil
{

    public static Item getItem(ConfigSection c)
    {
        if(c == null) return null;
        Item i = new Item(c.getInt("ID"),c.getInt("Meta"));
        i.setCustomName(PrintUtil.cc(c.getString("CustomName")));

        String lore = "";
        for (String s :c.getStringList("Lores"))
        {
            lore = PrintUtil.cc(lore + s + "\n");
        }
        i.setLore(lore);
        for (String s :c.getStringList("Enchant"))
        {
            String[] Enchs = s.split(":");
            if(Enchs.length == 2)
            {
                i.addEnchantment(getEnch(Integer.parseInt(Enchs[0]),Integer.parseInt(Enchs[1])));
            }
        }
        return i;
    }


    public static Enchantment getEnch(int ench, int level)
    {
        Enchantment enchantment = Enchantment.getEnchantment(ench);
        enchantment.setLevel(level,false);
        return enchantment;
    }

    public static boolean hasItem(Player p, Item item)
    {
        for (Item i : p.getInventory().getContents().values())
        {
            if((i != null) && (i.hasCustomName()) && (i.getId() == item.getId()) && (i.getCustomName().equals(item.getCustomName())))
            {
                return true;
            }
        }
        return false;
    }
}
