package hiiragi283.ragi_lib.main.base

import com.google.common.collect.Lists
import hiiragi283.ragi_lib.main.Reference
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemBase(ID: String?, maxMeta: Int) : Item() {

    //private変数の宣言
    private val maxMeta: Int

    //コンストラクタの宣言
    init {
        creativeTab = CreativeTabs.MISC //表示するクリエイティブタブの設定
        setRegistryName(Reference.MOD_ID, ID) //IDの設定
        unlocalizedName = ID.toString() //翻訳キーをIDから取得する
        this.maxMeta = maxMeta //メタデータの代入
        if (maxMeta != 0) this.setHasSubtypes(true) //メタデータを使用する
    }

    //メタデータを得るメソッド
    override fun getMetadata(damage: Int): Int {
        //代入した値とメタデータの最大値を比較し、小さい方を返す
        return damage.coerceAtMost(maxMeta)
    }

    //翻訳キーを得るメソッド
    override fun getUnlocalizedName(stack: ItemStack): String {
        //メタデータが0のみの場合、なにもしない
        return if (maxMeta == 0) super.getUnlocalizedName() else super.getUnlocalizedName() + "." + stack.metadata
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //listの定義
            val list: MutableList<ItemStack> = Lists.newArrayList()
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until maxMeta + 1) {
                list.add(ItemStack(this, 1, i))
            }
            //list内のすべてのアイテムをクリエイティブタブに登録
            subItems.addAll(list)
        }
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: List<String>, flag: ITooltipFlag) {
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}