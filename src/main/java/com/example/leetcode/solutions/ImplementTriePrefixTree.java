package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.TrieTree;
import com.example.leetcode.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Rxh
 * @Date: 2021/4/14 13:28
 * @Description: 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *  
 * <p>
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ImplementTriePrefixTree {

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
//
//        System.out.println(trie.search("apple"));
//        System.out.println(trie.search("app"));     // 返回 False
//        System.out.println(trie.startsWith("app")); // 返回 True
//        trie.insert("app");
//        System.out.println(trie.search("app"));     // 返回 True
//
        trie.insert("adopted");
        trie.insert("apple");
        trie.insert("apdsdsds");
        trie.insert("appdsds");
        trie.insert("are");

        trie.insert("dsafsa");
        trie.insert("distinguished");
        trie.insert("different");
        trie.insert("domestic");
//        System.out.println(trie.listRecommends("app", 8));

        // a - 97 A - 65

//        String str = "Breed Profile Colorpoint ShorthairColorpoint Shorthairs are the first cousins of the Siamese This br eed is distinguished by its elegance in sixteen different point colors beyond the four Siamese colors Half siblings to the Siamese by virtue of their foundation and continuing breeding with the Siamese the Colorpoint Shorthair is a hybrid breed of the Siamese Colorpoints circa are a far cry from their angular leggy descendants of today Today s Colorpoints are the same structural standard of the Siamese with the only difference being their unique point colors In the early breedings breeders concentrated on cats with red or cream restricted to the points face legs ears tails and genitals Early hybridizations with domestic shorthairs and refinement by concentrating the Siamese gene with the red gene produced the first of the colors to eventually be called Colorpoint Shorthairs To distinguish the new breed from the Siamese CFA breeders adopted the name Colorpoint Shorthair for registration purposes and through a painstaking process won recognition as a breed in The early cats who helped become the new breed were given the first color class of the Colorpoints called the solid points which are the red and cream points As time progressed and the early hybrids gained popularity the tabby versions of the Siamese were introduced into the Colorpoint Shorthair programs in the four Siamese colors In CFA these tabby pointed cats are called lynx points and are exhibited in their own lynx point class as seal lynx points chocolatelynx points blue lynx points lilac lynx points red lynx points and cream lynx points The tortie or parti colors are an interesting phenomenon of the hybridization process of the red gene Shortened to tortie or cream points this color class of the Colorpoint Shorthairs are exhibited as the parti colors They are memorable representatives of the breed because of their loving yet independent attitudes The parti colors are a by product of the red gene and come in the four Siamese colors with random mottling or blotching of red and or cream with the basic Siamese color They often also have what is called a blaze a symmetrical split of the red and or cream on one side of the face mask and the Siamese color such as seal on the other half Indeed this is a very striking appearance Because the red color gene is sex linked tortie or cream parti color points only come in females Color descriptions start with the primary Siamese color and add the mottling of red or cream Thus we have the seal tortie points chocolate tortie points blue cream points and lilac cream points When bred to a lynx parent the last four of the sixteen colors are the tabby or lynx versions of the parti color points i e the seal tortie lynx point chocolate tortie lynx point blue cream lynx point and lilac cream lynx point Like their Siamese cousins Colorpoint Shorthairs require little grooming and are especially good in households with allergies to cats since both breeds have little dander An occasional bath is recommended but allow the freshly bathed coat to air dry in a warm spot Do not blow dry but do brush the coat with the concave or short side of a small rubber brush to remove loose hair and make the coat lie smooth The coat can be finished by smoothing the coat with a chamois cloth Balanced diets high in protein are generally recommended since part of the natural beauty of the Colorpoints is their glistening muscular hard tubular bodies Heed the instructions of your cat s breeder when you acquire your Colorpoint Shorthair and you will be blessed with a long lived joyous companion Pricing on Colorpoint Shorthairs usually depends on type applicable markings and bloodlines distinguished by Grand Champion GC National National Breed and or Regional winning parentage NW BW RW or of Distinguished Merit parentage DM The DM title is achieved by the dam mother having produced five CFA grand champion premier alter or DM offspring or sire father having produced fifteen CFA grand champion premier or DM offspring Usually breeders make kittens available between twelve and sixteen weeks of age After twelve weeks kittens have had their basic inoculations and developed the physical and social stability needed for a new environment showing or being transported by air Keeping such a rare treasure indoors neutering or spaying and providing acceptable surfaces e g scratching posts for the natural behavior of scratching CFA disapproves of declawing or tendonectomy surgery are essential elements for maintaining a healthy long and joyful life Breed Standard Colorpoint Shorthair GENERAL the Colorpoint Shorthair is a medium sized svelte refined cat with long tapering lines very lithe but muscular Males may be proportionately larger The ideal is a cat with type identical to the Siamese but with its own distinct and unique colors While the color differences set it apart as a unique breed the purpose of the hybridization was to establish cats identical in type to the Siamese but with separate colors The Colorpoint Shorthair standard reflects this objective and preserves it s unique colors HEAD long tapering wedge Medium in size in good proportion to body The total wedge starts at the nose and flares out in straight lines to the tips of the ears forming a triangle with no break at the whiskers No less than the width of an eye between the eyes When the whiskers are smoothed back the underlying bone structure is apparent Allowance must be made for jowls in the stud cat SKULL flat In profile a long straight line is seen from the top of the head to the tip of the nose No bulge over eyes No dip in nose NECK long and slender NOSE long and straight A continuation of the forehead with no break MUZZLE fine wedge shaped EARS strikingly large pointed wide at base continuing the lines of the wedge EYES vivid blue in color no other shades or colors allowed Almond shaped Medium size Neither protruding nor recessed Slanted towards the nose in harmony with lines of wedge and ears Uncrossed CHIN and JAW medium in size Tip of chin lines with tip of nose in the same vertical plane Neither receding nor excessively massive BODY medium sized Graceful long and svelte A distinctive combination of fine bones and firm muscles Shoulders and hips continue same sleek lines of tubular body Hips never wider than shoulders Abdomen tight LEGS long and slim Hind legs higher than front In good proportion to body PAWS dainty small and oval Toes five in front and four behind TAIL long thin tapering to a fine point COAT short fine textured glossy Lying close to body CONDITION excellent physical condition Eyes clear Muscular strong and lithe Neither flabby nor bony Not fat COLOR Body subtle shading is permissible but clear color is preferable Allowance should be made for darker color in older cats as Colorpoint Shorthairs generally darken with age but there must be definite contrast between body color and points Points mask ears feet legs and tail dense and clearly defined All of the same shade Mask covers entire face including whisker pads and is connected to ears by tracings Mask should not extend over the top of the head No white hairs in points PENALIZE pigmentation of nose leather and or paw pads which is not consistent with the cat s particular color description Palpable and or visible protrusion of the cartilage at the end of the sternum DISQUALIFY any evidence of illness or poor health Weak hind legs Mouth breathing due to nasal obstruction or poor occlusion Emaciation Visible kink Eyes other than blue White toes and or feet Incorrect number of toes Malocclusion resulting in either undershot or overshot chin COLORPOINT SHORTHAIR COLORS Solid Color Point Colors RED POINT body clear white with any shading in the same tone as points Points bright apricot to deep red deeper shades preferred with lack of barring desirable Nose leather and paw pads flesh or coral pink CREAM POINT body clear white with any shading in the same tone as points Points pale buff cream to light pinkish cream lack of barring desirable Nose leather and paw pads flesh to coral pink Lynx Point Colors SEAL LYNX POINT body cream or pale fawn shading to lighter color on stomach and chest Body shading may take form of ghost striping or ticking Points seal brown bars distinct and separated by lighter background color ears seal brown with paler thumbprint in center Nose leather seal brown permitted pink edged in seal brown preferred Paw pads seal brown CHOCOLATE LYNX POINT body ivory Body shading may take form of ghost striping or ticking Points warm milk chocolate bars distinct and separated by lighter background color ears warm milk chocolate with paler thumbprint in center Nose leather cinnamon permitted pink edged in cinnamon preferred Paw pads cinnamon BLUE LYNX POINT body bluish white to platinum grey cold in tone shading to lighter color on stomach and chest Body shading may take form of ghost striping or ticking Points deep blue grey bars distinct and separated by lighter background color ears deep blue grey with paler thumbprint in center Nose leather slate colored permitted pink edged in slate preferred Paw pads slate colored LILAC LYNX POINT body glacial white Body shading may take form of ghost striping or ticking Points frosty grey with pinkish tone bars distinct and separated by lighter background color ears frosty grey with pinkish tone paler thumbprint in center Nose leather lavender pink permitted pink edged in lavender pink preferred Paw pads lavender pink RED LYNX POINT body white Body shading may take form of ghost striping or ticking Points deep red bars distinct and separated by lighter background color ears deep red paler thumbprint in center Nose leather and paw pads flesh or coral pink CREAM LYNX POINT body clear white Body shading may take form of ghost striping or ticking Points bars of pale buff cream to light pinkish cream distinct and separated by lighter background color ears pale buff cream to light pinkish cream paler thumbprint in center Nose leather and paw pads flesh to coral pink SEAL TORTIE LYNX POINT body cream or pale fawn shading to lighter color on stomach and chest Body shading may take form of ghost striping or ticking and or cream mottling Points seal brown bars distinct and separated by lighter background color ears seal brown with paler thumbprint in center Random mottling of red and or cream overlays the markings of the points Nose leather seal brown permitted pink edged in seal brown preferred flesh or coral pink mottling may be present Paw pads seal brown or seal brown mottled with flesh or coral pink NOTE these cats resemble lynx points more than tortie points CHOCOLATE TORTIE LYNX POINT body ivory Body shading may take form of ghost striping or ticking and or cream mottling Points warm milk chocolate bars distinct and separated by lighter background color ears warm milk chocolate with paler thumbprint in center Random mottling of red and or cream overlays the markings of the points Nose leather cinnamon permitted pink edged in cinnamon preferred flesh or coral pink mottling may be present Paw pads cinnamon or cinnamon mottled with flesh or coral pink NOTE these cats resemble lynx points more than tortie points BLUE CREAM LYNX POINT body bluish white to platinum grey cold in tone shading to lighter color on stomach and chest Body shading may take form of ghost striping or ticking and or cream mottling Points deep blue grey bars distinct and separated by lighter background color ears deep blue grey with paler thumbprint in center Random mottling of cream overlays the markings of the points Nose leather slate colored permitted pink edged in slate preferred flesh or coral pink mottling may be present Paw pads slate colored or slate mottled with flesh or coral pink NOTE these cats resemble lynx points more than tortie points LILAC CREAM LYNX POINT body glacial white Body shading may take form of ghost striping or ticking and or cream mottling Points frosty grey with pinkish tone bars distinct and separated by lighter background color ears frosty grey with pinkish tone paler thumbprint in center Random mottling of cream overlays the markings of the points Nose leather lavender pink permitted pink edged in lavender pink preferred flesh or coral pink mottling may be present Paw pads lavender pink or lavender pink mottled with flesh or coral pink NOTE these cats resemble lynx points more than tortie points Parti Color Point Colors SEAL TORTIE POINT body pale fawn to cream shading to lighter color on stomach and chest Body color may be mottled with cream in older cats Points seal brown randomly mottled with red and or cream Nose leather and paw pads seal brown flesh or coral pink mottling desirable CHOCOLATE TORTIE POINT body ivory may be mottled in older cats Points warm milk chocolate randomly mottled with red and or cream Nose leather and paw pads cinnamon flesh or coral pink mottling desirable BLUE CREAM POINT body bluish white to platinum grey cold in tone shading to lighter color on stomach and chest Body color may be mottled in older cats Points deep blue grey randomly mottled with cream Nose leather and paw pads slate colored flesh or coral pink mottling desirable LILAC CREAM POINT body glacial white mottling if any in the shade of the points Points frosty grey with pinkish tone randomly mottled with pale cream Nose leather and paw pads lavender pink flesh or coral pink mottling desirable The following information is for reference purposes only and not an official part of the CFA Show Standard Colorpoint Shorthair Color Class Numbers Note Solid Color Point Cream or Red Blue Blue Cream Chocolate Chocolate Tortie Cream Lilac Lilac Cream Red Seal or Seal Tortie Blue Cream Chocolate Tortie Lilac Cream or Seal Tortie Colorpoint Shorthair allowable outcross breeds Siamese for litters born before January ";

//        String[] split = str.split(" ");
//        for (String s : split) {
//            trie.insert(s);
//        }


        System.out.println(Util.printFeedLine(trie.listAll()));
    }


    /**
     * 使用布隆过滤器模拟
     */
    @Score(time = Score.S.A, memory = Score.S.B)
    static class Trie {

        TrieTree root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieTree();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            root.insert(word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return root.search(word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return root.startsWith(prefix);
        }

        public List<String> listRecommends(String input, int limit) {
            return root.listRecommends(input, limit);
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
