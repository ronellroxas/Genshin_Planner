package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.mobdeve.s13.g26.genshinplanner.adapters.CharacterListAdapter;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.models.Character;
import com.mobdeve.s13.g26.genshinplanner.models.Item;
import com.mobdeve.s13.g26.genshinplanner.utils.AssetsHelper;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CharacterListActivity extends AppCompatActivity {

    private ArrayList<Character> charList;
    private AssetsHelper assetsHelper;
    private RecyclerView rvList;
    private CharacterListAdapter clAdapter;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assetsHelper = new AssetsHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        charList = new ArrayList<>();

        this.tvTitle = findViewById(R.id.tv_list_title);
        this.tvTitle.setText("Characters");

        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        //getImageResources();    //get all img filenames in res folder (self-declared)
        loadData();
        this.rvList = findViewById(R.id.rv_list);
        this.clAdapter = new CharacterListAdapter(this.charList);

        this.rvList.setLayoutManager(new GridLayoutManager(this, 3));
        this.rvList.setAdapter(this.clAdapter);
    }

    private void loadData(){
        try{
            charList.addAll(assetsHelper.getCharacterAssets());

            ArrayList<Integer> albedo_ascen_mats = new ArrayList<>();
            albedo_ascen_mats.add(R.drawable.item_character_ascension_prithiva_topaz_gemstone);
            albedo_ascen_mats.add(R.drawable.item_boss_material_basalt_pillar);
            albedo_ascen_mats.add(R.drawable.item_local_specialties_cecilia);
            albedo_ascen_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);

            ArrayList<Integer> albedo_talent_mats = new ArrayList<>();
            albedo_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            albedo_talent_mats.add(R.drawable.item_talent_boss_tusk_of_monoceros_caeli);
            albedo_talent_mats.add(R.drawable.item_tal_crown);
            albedo_talent_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);
            charList.get(0).setAscension_mats(albedo_ascen_mats);
            charList.get(0).setTalent_mats(albedo_talent_mats);

            ArrayList<Integer> amber_ascen_mats = new ArrayList<>();
            amber_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            amber_ascen_mats.add(R.drawable.item_boss_material_everflame_seed);
            amber_ascen_mats.add(R.drawable.item_local_specialties_small_lamp_grass);
            amber_ascen_mats.add(R.drawable.item_common_ascension_weathered_arrow);

            ArrayList<Integer> amber_talent_mats = new ArrayList<>();
            amber_talent_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            amber_talent_mats.add(R.drawable.item_talent_boss_dvalins_sigh);
            amber_talent_mats.add(R.drawable.item_tal_crown);
            amber_talent_mats.add(R.drawable.item_common_ascension_weathered_arrow);
            charList.get(1).setAscension_mats(amber_ascen_mats);
            charList.get(1).setTalent_mats(amber_talent_mats);

            ArrayList<Integer> ayaka_ascen_mats = new ArrayList<>();
            ayaka_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            ayaka_ascen_mats.add(R.drawable.item_boss_material_perpetual_heart);
            ayaka_ascen_mats.add(R.drawable.item_local_specialties_sakura_bloom);
            ayaka_ascen_mats.add(R.drawable.item_common_ascension_famed_handguard);

            ArrayList<Integer> ayaka_talent_mats = new ArrayList<>();
            ayaka_talent_mats.add(R.drawable.item_talent_book_teachings_of_elegance);
            ayaka_talent_mats.add(R.drawable.item_talent_boss_bloodjade_branch);
            ayaka_talent_mats.add(R.drawable.item_tal_crown);
            ayaka_talent_mats.add(R.drawable.item_common_ascension_famed_handguard);
            charList.get(2).setAscension_mats(ayaka_ascen_mats);
            charList.get(2).setTalent_mats(ayaka_talent_mats);

            ArrayList<Integer> barbara_ascen_mats = new ArrayList<>();
            barbara_ascen_mats.add(R.drawable.item_character_ascension_varunada_lazurite_gemstone);
            barbara_ascen_mats.add(R.drawable.item_boss_material_cleansing_heart);
            barbara_ascen_mats.add(R.drawable.item_local_specialties_philanemo_mushroom);
            barbara_ascen_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);

            ArrayList<Integer> barbara_talent_mats = new ArrayList<>();
            barbara_talent_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            barbara_talent_mats.add(R.drawable.item_talent_boss_ring_of_boreas);
            barbara_talent_mats.add(R.drawable.item_tal_crown);
            barbara_talent_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);
            charList.get(3).setAscension_mats(barbara_ascen_mats);
            charList.get(3).setTalent_mats(barbara_talent_mats);

            ArrayList<Integer> beidou_ascen_mats = new ArrayList<>();
            beidou_ascen_mats.add(R.drawable.item_character_ascension_vajrada_amethyst_gemstone);
            beidou_ascen_mats.add(R.drawable.item_boss_material_lightning_prism);
            beidou_ascen_mats.add(R.drawable.item_local_specialties_noctilucous_jade);
            beidou_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> beidou_talent_mats = new ArrayList<>();
            beidou_talent_mats.add(R.drawable.item_talent_book_teachings_of_gold);
            beidou_talent_mats.add(R.drawable.item_talent_boss_dvalins_sigh);
            beidou_talent_mats.add(R.drawable.item_tal_crown);
            beidou_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(4).setAscension_mats(beidou_ascen_mats);
            charList.get(4).setTalent_mats(beidou_talent_mats);

            ArrayList<Integer> bennett_ascen_mats = new ArrayList<>();
            bennett_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            bennett_ascen_mats.add(R.drawable.item_boss_material_everflame_seed);
            bennett_ascen_mats.add(R.drawable.item_local_specialties_windwheel_aster);
            bennett_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> bennett_talent_mats = new ArrayList<>();
            bennett_talent_mats.add(R.drawable.item_talent_book_teachings_of_resistance);
            bennett_talent_mats.add(R.drawable.item_talent_boss_dvalins_plume);
            bennett_talent_mats.add(R.drawable.item_tal_crown);
            bennett_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(5).setAscension_mats(bennett_ascen_mats);
            charList.get(5).setTalent_mats(bennett_talent_mats);

            ArrayList<Integer> chongyun_ascen_mats = new ArrayList<>();
            chongyun_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            chongyun_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            chongyun_ascen_mats.add(R.drawable.item_local_specialties_cor_lapis);
            chongyun_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> chongyun_talent_mats = new ArrayList<>();
            chongyun_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            chongyun_talent_mats.add(R.drawable.item_talent_boss_dvalins_sigh);
            chongyun_talent_mats.add(R.drawable.item_tal_crown);
            chongyun_talent_mats.add(R.drawable.item_common_ascension_ominous_mask);
            charList.get(6).setAscension_mats(chongyun_ascen_mats);
            charList.get(6).setTalent_mats(chongyun_talent_mats);

            ArrayList<Integer> diluc_ascen_mats = new ArrayList<>();
            diluc_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            diluc_ascen_mats.add(R.drawable.item_boss_material_everflame_seed);
            diluc_ascen_mats.add(R.drawable.item_local_specialties_small_lamp_grass);
            diluc_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> diluc_talent_mats = new ArrayList<>();
            diluc_talent_mats.add(R.drawable.item_talent_book_teachings_of_resistance);
            diluc_talent_mats.add(R.drawable.item_talent_boss_dvalins_plume);
            diluc_talent_mats.add(R.drawable.item_tal_crown);
            diluc_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(7).setTalent_mats(diluc_talent_mats);
            charList.get(7).setAscension_mats(diluc_ascen_mats);

            ArrayList<Integer> diona_ascen_mats = new ArrayList<>();
            diona_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            diona_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            diona_ascen_mats.add(R.drawable.item_local_specialties_calla_lily);
            diona_ascen_mats.add(R.drawable.item_common_ascension_weathered_arrow);

            ArrayList<Integer> diona_talent_mats = new ArrayList<>();
            diona_talent_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            diona_talent_mats.add(R.drawable.item_talent_boss_shard_of_a_foul_legacy);
            diona_talent_mats.add(R.drawable.item_tal_crown);
            diona_talent_mats.add(R.drawable.item_common_ascension_weathered_arrow);
            charList.get(8).setAscension_mats(diona_ascen_mats);
            charList.get(8).setTalent_mats(diona_talent_mats);

            ArrayList<Integer> eula_ascen_mats = new ArrayList<>();
            eula_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            eula_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            eula_ascen_mats.add(R.drawable.item_local_specialties_dandelion_seed);
            eula_ascen_mats.add(R.drawable.item_common_ascension_weathered_arrow);

            ArrayList<Integer> eula_talent_mats = new ArrayList<>();
            eula_talent_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            eula_talent_mats.add(R.drawable.item_talent_boss_shard_of_a_foul_legacy);
            eula_talent_mats.add(R.drawable.item_tal_crown);
            eula_talent_mats.add(R.drawable.item_common_ascension_weathered_arrow);
            charList.get(9).setAscension_mats(eula_ascen_mats);
            charList.get(9).setTalent_mats(eula_talent_mats);

            ArrayList<Integer> fischl_ascen_mats = new ArrayList<>();
            fischl_ascen_mats.add(R.drawable.item_character_ascension_vajrada_amethyst_gemstone);
            fischl_ascen_mats.add(R.drawable.item_boss_material_lightning_prism);
            fischl_ascen_mats.add(R.drawable.item_local_specialties_small_lamp_grass);
            fischl_ascen_mats.add(R.drawable.item_common_ascension_weathered_arrow);

            ArrayList<Integer> fischl_talent_mats = new ArrayList<>();
            fischl_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            fischl_talent_mats.add(R.drawable.item_talent_boss_spirit_locket_of_boreas);
            fischl_talent_mats.add(R.drawable.item_tal_crown);
            fischl_talent_mats.add(R.drawable.item_common_ascension_weathered_arrow);
            charList.get(10).setAscension_mats(fischl_ascen_mats);
            charList.get(10).setTalent_mats(fischl_talent_mats);

            ArrayList<Integer> ganyu_ascen_mats = new ArrayList<>();
            ganyu_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            ganyu_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            ganyu_ascen_mats.add(R.drawable.item_local_specialties_qingxin);
            ganyu_ascen_mats.add(R.drawable.item_common_ascension_energy_nectar);

            ArrayList<Integer> ganyu_talent_mats = new ArrayList<>();
            ganyu_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            ganyu_talent_mats.add(R.drawable.item_talent_boss_shadow_of_the_warrior);
            ganyu_talent_mats.add(R.drawable.item_tal_crown);
            ganyu_talent_mats.add(R.drawable.item_common_ascension_energy_nectar);
            charList.get(11).setAscension_mats(ganyu_ascen_mats);
            charList.get(11).setTalent_mats(ganyu_talent_mats);

            ArrayList<Integer> hutao_ascen_mats = new ArrayList<>();
            hutao_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            hutao_ascen_mats.add(R.drawable.item_boss_material_juvenile_jade);
            hutao_ascen_mats.add(R.drawable.item_local_specialties_silk_flower);
            hutao_ascen_mats.add(R.drawable.item_common_ascension_energy_nectar);

            ArrayList<Integer> hutao_talent_mats = new ArrayList<>();
            hutao_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            hutao_talent_mats.add(R.drawable.item_talent_boss_shard_of_a_foul_legacy);
            hutao_talent_mats.add(R.drawable.item_tal_crown);
            hutao_talent_mats.add(R.drawable.item_common_ascension_energy_nectar);
            charList.get(12).setAscension_mats(hutao_ascen_mats);
            charList.get(12).setTalent_mats(hutao_talent_mats);

            ArrayList<Integer> jean_ascen_mats = new ArrayList<>();
            jean_ascen_mats.add(R.drawable.item_character_ascension_vayuda_turquoise_gemstone);
            jean_ascen_mats.add(R.drawable.item_boss_material_hurricane_seed);
            jean_ascen_mats.add(R.drawable.item_local_specialties_dandelion_seed);
            jean_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> jean_talent_mats = new ArrayList<>();
            jean_talent_mats.add(R.drawable.item_talent_book_teachings_of_resistance);
            jean_talent_mats.add(R.drawable.item_talent_boss_dvalins_plume);
            jean_talent_mats.add(R.drawable.item_tal_crown);
            jean_talent_mats.add(R.drawable.item_common_ascension_ominous_mask);
            charList.get(13).setAscension_mats(jean_ascen_mats);
            charList.get(13).setTalent_mats(jean_talent_mats);

            ArrayList<Integer> kaeya_ascen_mats = new ArrayList<>();
            kaeya_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            kaeya_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            kaeya_ascen_mats.add(R.drawable.item_local_specialties_calla_lily);
            kaeya_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> kaeya_talent_mats = new ArrayList<>();
            kaeya_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            kaeya_talent_mats.add(R.drawable.item_talent_boss_spirit_locket_of_boreas);
            kaeya_talent_mats.add(R.drawable.item_tal_crown);
            kaeya_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(14).setAscension_mats(kaeya_ascen_mats);
            charList.get(14).setTalent_mats(kaeya_talent_mats);

            ArrayList<Integer> kazuha_ascen_mats = new ArrayList<>();
            kazuha_ascen_mats.add(R.drawable.item_character_ascension_vayuda_turquoise_gemstone);
            kazuha_ascen_mats.add(R.drawable.item_boss_material_marionette_core);
            kazuha_ascen_mats.add(R.drawable.item_local_specialties_sea_ganoderma);
            kazuha_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> kazuha_talent_mats = new ArrayList<>();
            kazuha_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            kazuha_talent_mats.add(R.drawable.item_talent_boss_gilded_scale);
            kazuha_talent_mats.add(R.drawable.item_tal_crown);
            kazuha_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(15).setAscension_mats(kazuha_ascen_mats);
            charList.get(15).setTalent_mats(kazuha_talent_mats);

            ArrayList<Integer> keqing_ascen_mats = new ArrayList<>();
            keqing_ascen_mats.add(R.drawable.item_character_ascension_vajrada_amethyst_gemstone);
            keqing_ascen_mats.add(R.drawable.item_boss_material_lightning_prism);
            keqing_ascen_mats.add(R.drawable.item_local_specialties_cor_lapis);
            keqing_ascen_mats.add(R.drawable.item_common_ascension_energy_nectar);

            ArrayList<Integer> keqing_talent_mats = new ArrayList<>();
            keqing_talent_mats.add(R.drawable.item_talent_book_teachings_of_prosperity);
            keqing_talent_mats.add(R.drawable.item_talent_boss_ring_of_boreas);
            keqing_talent_mats.add(R.drawable.item_tal_crown);
            keqing_talent_mats.add(R.drawable.item_common_ascension_energy_nectar);
            charList.get(16).setAscension_mats(keqing_ascen_mats);
            charList.get(16).setTalent_mats(keqing_talent_mats);

            ArrayList<Integer> klee_ascen_mats = new ArrayList<>();
            klee_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            klee_ascen_mats.add(R.drawable.item_boss_material_everflame_seed);
            klee_ascen_mats.add(R.drawable.item_local_specialties_philanemo_mushroom);
            klee_ascen_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);

            ArrayList<Integer> klee_talent_mats = new ArrayList<>();
            klee_talent_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            klee_talent_mats.add(R.drawable.item_talent_boss_ring_of_boreas);
            klee_talent_mats.add(R.drawable.item_tal_crown);
            klee_talent_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);
            charList.get(17).setAscension_mats(klee_ascen_mats);
            charList.get(17).setTalent_mats(klee_talent_mats);

            ArrayList<Integer> lisa_ascen_mats = new ArrayList<>();
            lisa_ascen_mats.add(R.drawable.item_character_ascension_vajrada_amethyst_gemstone);
            lisa_ascen_mats.add(R.drawable.item_boss_material_lightning_prism);
            lisa_ascen_mats.add(R.drawable.item_local_specialties_valberry);
            lisa_ascen_mats.add(R.drawable.item_common_ascension_slime_condensate);

            ArrayList<Integer> lisa_talent_mats = new ArrayList<>();
            lisa_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            lisa_talent_mats.add(R.drawable.item_talent_boss_dvalins_claw);
            lisa_talent_mats.add(R.drawable.item_tal_crown);
            lisa_talent_mats.add(R.drawable.item_common_ascension_slime_condensate);
            charList.get(18).setAscension_mats(lisa_ascen_mats);
            charList.get(18).setTalent_mats(lisa_talent_mats);

            ArrayList<Integer> mona_ascen_mats = new ArrayList<>();
            mona_ascen_mats.add(R.drawable.item_character_ascension_varunada_lazurite_gemstone);
            mona_ascen_mats.add(R.drawable.item_boss_material_cleansing_heart);
            mona_ascen_mats.add(R.drawable.item_local_specialties_philanemo_mushroom);
            mona_ascen_mats.add(R.drawable.item_common_ascension_energy_nectar);

            ArrayList<Integer> mona_talent_mats = new ArrayList<>();
            mona_talent_mats.add(R.drawable.item_talent_book_teachings_of_resistance);
            mona_talent_mats.add(R.drawable.item_talent_boss_ring_of_boreas);
            mona_talent_mats.add(R.drawable.item_tal_crown);
            mona_talent_mats.add(R.drawable.item_common_ascension_energy_nectar);
            charList.get(19).setAscension_mats(mona_ascen_mats);
            charList.get(19).setTalent_mats(mona_talent_mats);

            ArrayList<Integer> ningguang_ascen_mats = new ArrayList<>();
            ningguang_ascen_mats.add(R.drawable.item_character_ascension_prithiva_topaz_gemstone);
            ningguang_ascen_mats.add(R.drawable.item_boss_material_basalt_pillar);
            ningguang_ascen_mats.add(R.drawable.item_local_specialties_glaze_lily);
            ningguang_ascen_mats.add(R.drawable.item_common_ascension_lieutenants_insignia);

            ArrayList<Integer> ningguang_talent_mats = new ArrayList<>();
            ningguang_talent_mats.add(R.drawable.item_talent_book_teachings_of_prosperity);
            ningguang_talent_mats.add(R.drawable.item_talent_boss_spirit_locket_of_boreas);
            ningguang_talent_mats.add(R.drawable.item_tal_crown);
            ningguang_talent_mats.add(R.drawable.item_common_ascension_lieutenants_insignia);
            charList.get(20).setAscension_mats(ningguang_ascen_mats);
            charList.get(20).setTalent_mats(ningguang_talent_mats);

            ArrayList<Integer> noelle_ascen_mats = new ArrayList<>();
            noelle_ascen_mats.add(R.drawable.item_character_ascension_prithiva_topaz_gemstone);
            noelle_ascen_mats.add(R.drawable.item_boss_material_basalt_pillar);
            noelle_ascen_mats.add(R.drawable.item_local_specialties_valberry);
            noelle_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> noelle_talent_mats = new ArrayList<>();
            noelle_talent_mats.add(R.drawable.item_talent_book_teachings_of_resistance);
            noelle_talent_mats.add(R.drawable.item_talent_boss_dvalins_claw);
            noelle_talent_mats.add(R.drawable.item_tal_crown);
            noelle_talent_mats.add(R.drawable.item_common_ascension_ominous_mask);
            charList.get(21).setAscension_mats(noelle_ascen_mats);
            charList.get(21).setTalent_mats(noelle_talent_mats);

            ArrayList<Integer> qiqi_ascen_mats = new ArrayList<>();
            qiqi_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            qiqi_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            qiqi_ascen_mats.add(R.drawable.item_local_specialties_violetgrass);
            qiqi_ascen_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);

            ArrayList<Integer> qiqi_talent_mats = new ArrayList<>();
            qiqi_talent_mats.add(R.drawable.item_talent_book_teachings_of_prosperity);
            qiqi_talent_mats.add(R.drawable.item_talent_boss_tail_of_boreas);
            qiqi_talent_mats.add(R.drawable.item_tal_crown);
            qiqi_talent_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);
            charList.get(22).setAscension_mats(qiqi_ascen_mats);
            charList.get(22).setTalent_mats(qiqi_talent_mats);

            ArrayList<Integer> razor_ascen_mats = new ArrayList<>();
            razor_ascen_mats.add(R.drawable.item_character_ascension_vajrada_amethyst_gemstone);
            razor_ascen_mats.add(R.drawable.item_boss_material_lightning_prism);
            razor_ascen_mats.add(R.drawable.item_local_specialties_wolfhook);
            razor_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> razor_talent_mats = new ArrayList<>();
            razor_talent_mats.add(R.drawable.item_talent_book_teachings_of_resistance);
            razor_talent_mats.add(R.drawable.item_talent_boss_dvalins_claw);
            razor_talent_mats.add(R.drawable.item_tal_crown);
            razor_talent_mats.add(R.drawable.item_common_ascension_ominous_mask);
            charList.get(23).setAscension_mats(razor_ascen_mats);
            charList.get(23).setTalent_mats(razor_talent_mats);

            ArrayList<Integer> rosaria_ascen_mats = new ArrayList<>();
            rosaria_ascen_mats.add(R.drawable.item_character_ascension_shivada_jade__gemstone);
            rosaria_ascen_mats.add(R.drawable.item_boss_material_hoarfrost_core);
            rosaria_ascen_mats.add(R.drawable.item_local_specialties_valberry);
            rosaria_ascen_mats.add(R.drawable.item_common_ascension_lieutenants_insignia);

            ArrayList<Integer> rosaria_talent_mats = new ArrayList<>();
            rosaria_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            rosaria_talent_mats.add(R.drawable.item_talent_boss_shadow_of_the_warrior);
            rosaria_talent_mats.add(R.drawable.item_tal_crown);
            rosaria_talent_mats.add(R.drawable.item_common_ascension_lieutenants_insignia);
            charList.get(24).setAscension_mats(rosaria_ascen_mats);
            charList.get(24).setTalent_mats(rosaria_talent_mats);

            ArrayList<Integer> sucrose_ascen_mats = new ArrayList<>();
            sucrose_ascen_mats.add(R.drawable.item_character_ascension_vayuda_turquoise_gemstone);
            sucrose_ascen_mats.add(R.drawable.item_boss_material_hurricane_seed);
            sucrose_ascen_mats.add(R.drawable.item_local_specialties_windwheel_aster);
            sucrose_ascen_mats.add(R.drawable.item_common_ascension_energy_nectar);

            ArrayList<Integer> sucrose_talent_mats = new ArrayList<>();
            sucrose_talent_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            sucrose_talent_mats.add(R.drawable.item_talent_boss_spirit_locket_of_boreas);
            sucrose_talent_mats.add(R.drawable.item_tal_crown);
            sucrose_talent_mats.add(R.drawable.item_common_ascension_energy_nectar);
            charList.get(25).setAscension_mats(sucrose_ascen_mats);
            charList.get(25).setTalent_mats(sucrose_talent_mats);

            ArrayList<Integer> childe_ascen_mats = new ArrayList<>();
            childe_ascen_mats.add(R.drawable.item_character_ascension_varunada_lazurite_gemstone);
            childe_ascen_mats.add(R.drawable.item_boss_material_cleansing_heart);
            childe_ascen_mats.add(R.drawable.item_local_specialties_starconch);
            childe_ascen_mats.add(R.drawable.item_common_ascension_lieutenants_insignia);

            ArrayList<Integer> childe_talent_mats = new ArrayList<>();
            childe_talent_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            childe_talent_mats.add(R.drawable.item_talent_boss_shard_of_a_foul_legacy);
            childe_talent_mats.add(R.drawable.item_tal_crown);
            childe_talent_mats.add(R.drawable.item_common_ascension_lieutenants_insignia);
            charList.get(26).setAscension_mats(childe_ascen_mats);
            charList.get(26).setTalent_mats(childe_talent_mats);

            ArrayList<Integer> anemo_ascen_mats = new ArrayList<>();
            anemo_ascen_mats.add(R.drawable.item_character_ascension_brilliant_diamond_gemstone);
            anemo_ascen_mats.add(R.drawable.item_talent_book_teachings_of_freedom);
            anemo_ascen_mats.add(R.drawable.item_local_specialties_windwheel_aster);
            anemo_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> anemo_talent_mats = new ArrayList<>();
            anemo_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            anemo_talent_mats.add(R.drawable.item_talent_boss_dvalins_sigh);
            anemo_talent_mats.add(R.drawable.item_tal_crown);
            anemo_talent_mats.add(R.drawable.item_common_ascension_forbidden_curse_scroll);
            charList.get(27).setAscension_mats(anemo_ascen_mats);
            charList.get(27).setTalent_mats(anemo_talent_mats);

            ArrayList<Integer> geo_ascen_mats = new ArrayList<>();
            geo_ascen_mats.add(R.drawable.item_character_ascension_brilliant_diamond_gemstone);
            geo_ascen_mats.add(R.drawable.item_talent_book_teachings_of_prosperity);
            geo_ascen_mats.add(R.drawable.item_local_specialties_windwheel_aster);
            geo_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> geo_talent_mats = new ArrayList<>();
            geo_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            geo_talent_mats.add(R.drawable.item_talent_boss_tail_of_boreas);
            geo_talent_mats.add(R.drawable.item_tal_crown);
            geo_talent_mats.add(R.drawable.item_common_ascension_weathered_arrow);
            charList.get(28).setAscension_mats(geo_ascen_mats);
            charList.get(28).setTalent_mats(geo_talent_mats);

            ArrayList<Integer> venti_ascen_mats = new ArrayList<>();
            venti_ascen_mats.add(R.drawable.item_character_ascension_vayuda_turquoise_gemstone);
            venti_ascen_mats.add(R.drawable.item_boss_material_hurricane_seed);
            venti_ascen_mats.add(R.drawable.item_local_specialties_cecilia);
            venti_ascen_mats.add(R.drawable.item_common_ascension_slime_condensate);

            ArrayList<Integer> venti_talent_mats = new ArrayList<>();
            venti_talent_mats.add(R.drawable.item_talent_book_teachings_of_ballad);
            venti_talent_mats.add(R.drawable.item_talent_boss_tail_of_boreas);
            venti_talent_mats.add(R.drawable.item_tal_crown);
            venti_talent_mats.add(R.drawable.item_common_ascension_slime_condensate);
            charList.get(29).setAscension_mats(venti_ascen_mats);
            charList.get(29).setTalent_mats(venti_talent_mats);

            ArrayList<Integer> xiangling_ascen_mats = new ArrayList<>();
            xiangling_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            xiangling_ascen_mats.add(R.drawable.item_boss_material_everflame_seed);
            xiangling_ascen_mats.add(R.drawable.item_local_specialties_jueyen_chili);
            xiangling_ascen_mats.add(R.drawable.item_common_ascension_slime_condensate);

            ArrayList<Integer> xiangling_talent_mats = new ArrayList<>();
            xiangling_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            xiangling_talent_mats.add(R.drawable.item_talent_boss_dvalins_claw);
            xiangling_talent_mats.add(R.drawable.item_tal_crown);
            xiangling_talent_mats.add(R.drawable.item_common_ascension_slime_condensate);
            charList.get(30).setAscension_mats(xiangling_ascen_mats);
            charList.get(30).setTalent_mats(xiangling_talent_mats);

            ArrayList<Integer> xiao_ascen_mats = new ArrayList<>();
            xiao_ascen_mats.add(R.drawable.item_character_ascension_vayuda_turquoise_gemstone);
            xiao_ascen_mats.add(R.drawable.item_boss_material_juvenile_jade);
            xiao_ascen_mats.add(R.drawable.item_local_specialties_qingxin);
            xiao_ascen_mats.add(R.drawable.item_common_ascension_slime_condensate);

            ArrayList<Integer> xiao_talent_mats = new ArrayList<>();
            xiao_talent_mats.add(R.drawable.item_talent_book_teachings_of_diligence);
            xiao_talent_mats.add(R.drawable.item_talent_boss_shadow_of_the_warrior);
            xiao_talent_mats.add(R.drawable.item_tal_crown);
            xiao_talent_mats.add(R.drawable.item_common_ascension_slime_condensate);
            charList.get(31).setAscension_mats(xiao_ascen_mats);
            charList.get(31).setTalent_mats(xiao_talent_mats);

            ArrayList<Integer> xingqui_ascen_mats = new ArrayList<>();
            xingqui_ascen_mats.add(R.drawable.item_character_ascension_varunada_lazurite_gemstone);
            xingqui_ascen_mats.add(R.drawable.item_boss_material_cleansing_heart);
            xingqui_ascen_mats.add(R.drawable.item_local_specialties_silk_flower);
            xingqui_ascen_mats.add(R.drawable.item_common_ascension_ominous_mask);

            ArrayList<Integer> xingqui_talent_mats = new ArrayList<>();
            xingqui_talent_mats.add(R.drawable.item_talent_book_teachings_of_gold);
            xingqui_talent_mats.add(R.drawable.item_talent_boss_tail_of_boreas);
            xingqui_talent_mats.add(R.drawable.item_tal_crown);
            xingqui_talent_mats.add(R.drawable.item_common_ascension_ominous_mask);
            charList.get(32).setAscension_mats(xingqui_ascen_mats);
            charList.get(32).setTalent_mats(xingqui_talent_mats);

            ArrayList<Integer> xinyan_ascen_mats = new ArrayList<>();
            xinyan_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            xinyan_ascen_mats.add(R.drawable.item_boss_material_everflame_seed);
            xinyan_ascen_mats.add(R.drawable.item_local_specialties_violetgrass);
            xinyan_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> xinyan_talent_mats = new ArrayList<>();
            xinyan_talent_mats.add(R.drawable.item_talent_book_teachings_of_gold);
            xinyan_talent_mats.add(R.drawable.item_talent_boss_tusk_of_monoceros_caeli);
            xinyan_talent_mats.add(R.drawable.item_tal_crown);
            xinyan_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(33).setAscension_mats(xinyan_ascen_mats);
            charList.get(33).setTalent_mats(xinyan_talent_mats);

            ArrayList<Integer> yanfei_ascen_mats = new ArrayList<>();
            yanfei_ascen_mats.add(R.drawable.item_character_ascension_agnidus_agate_gemstone);
            yanfei_ascen_mats.add(R.drawable.item_boss_material_juvenile_jade);
            yanfei_ascen_mats.add(R.drawable.item_local_specialties_noctilucous_jade);
            yanfei_ascen_mats.add(R.drawable.item_common_ascension_gold_insignia);

            ArrayList<Integer> yanfei_talent_mats = new ArrayList<>();
            yanfei_talent_mats.add(R.drawable.item_talent_book_teachings_of_gold);
            yanfei_talent_mats.add(R.drawable.item_talent_boss_bloodjade_branch);
            yanfei_talent_mats.add(R.drawable.item_tal_crown);
            yanfei_talent_mats.add(R.drawable.item_common_ascension_gold_insignia);
            charList.get(34).setAscension_mats(yanfei_ascen_mats);
            charList.get(34).setTalent_mats(yanfei_talent_mats);

            ArrayList<Integer> zhongli_ascen_mats = new ArrayList<>();
            zhongli_ascen_mats.add(R.drawable.item_character_ascension_prithiva_topaz_gemstone);
            zhongli_ascen_mats.add(R.drawable.item_boss_material_basalt_pillar);
            zhongli_ascen_mats.add(R.drawable.item_local_specialties_cor_lapis);
            zhongli_ascen_mats.add(R.drawable.item_common_ascension_slime_condensate);

            ArrayList<Integer> zhongli_talent_mats = new ArrayList<>();
            zhongli_talent_mats.add(R.drawable.item_talent_book_teachings_of_gold);
            zhongli_talent_mats.add(R.drawable.item_talent_boss_tusk_of_monoceros_caeli);
            zhongli_talent_mats.add(R.drawable.item_tal_crown);
            zhongli_talent_mats.add(R.drawable.item_common_ascension_slime_condensate);
            charList.get(35).setAscension_mats(zhongli_ascen_mats);
            charList.get(35).setTalent_mats(zhongli_talent_mats);
        }catch (JSONException e){

        }
    }

}