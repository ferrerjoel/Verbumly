package com.example.epicuntitledmobilegame.data

import android.util.Log
import kotlin.random.Random

object WordData {
    private var words : HashMap<Int, String> = HashMap<Int, String>()
    init
    {
        println("Singleton class invoked.")

        allocateData()
    }

    public fun getRandomWord(letters: Int): String {

        if (words.containsKey(letters)){
            val randomPosition = Random.nextInt(0, (words[letters]!!.length  / letters))
            Log.d("DEBUG", " " + words[letters]!!.length + " " + (words[letters]!!.length / letters) + " " + randomPosition + " " + randomPosition * letters + " " + randomPosition * letters + letters )
            return words[letters]!!.substring(randomPosition * letters, randomPosition * letters + letters)
        }

        return ""

    }

    private fun allocateData(){
        words[4] = "ableacidaideaidsallyalsoarabareaarmyautoawaybabybackbakeballbandbankbasebeanbearbeatbeerbellbeltbendbestbikebillbindbirdbiteblowblueboatbodybombbondbonebookboombootbornbossbothbowlbuckburnburybusycakecallcampcardcarecasecashcastcellchefchipcitecityclubcluecoalcoatcodecoldcomecookcoolcopecopycorecorncostcrewcropdaredarkdatadatedeaddealdeardebtdeckdeepdeerdenydeskdietdirtdishdoordowndragdrawdropdrugdustdutyeachearneaseeasteasyedgeelseeveneverfacefactfadefailfairfallfarmfastfatefearfeedfeelfilefillfilmfindfinefirefirmfishfiveflagflatfleeflowfolkfoodfootformfourfreefromfuelfullfundgaingameganggategazegeargenegiftgirlgivegladgoalgoldgolfgoodgrabgraygrowhairhalfhallhandhanghardhatehaveheadhearheatheelhellhelphereherohidehighhillhireholdholeholyhomehopehosthourhugehurtideaintoironitemjailjoinjokejumpjuryjustkeepkickkillkindkingkisskneeknowlackladylakelandlastlatelawnleadleafleanleftlesslifeliftlikelinelinklistliveloadloanlocklonglookloselosslostlotsloudlovelucklungmailmainmakemalemallmanymarkmaskmassmathmealmeanmeatmeetmenumeremessmilkmindminemissmodemoodmoonmoremostmovemuchmustmythnamenearneckneednewsnextniceninenonenosenoteoddsokayonceonlyontoopenovenoverpacepackpagepainpairpalepalmpantparkpartpasspastpathpeakpeerpickpilepinepinkpipeplanplayplotpluspoempoetpolepollpoolpoorportposepostpourpraypullpurepushquitracerailrainrankrareratereadrealrelyrestricerichrideringriseriskroadrockrolerollroofroomrootroperoserulerushsafesakesalesaltsamesandsaveseatseedseekseemselfsellsendshipshitshoeshopshotshowshutsicksidesighsignsingsinksitesizeskinslipslowsnapsnowsoftsoilsomesongsoonsortsoulsoupspinspotstarstaystepstirstopsuchsuitsureswimtailtaketaletalktalltanktapetaskteamtearteentelltendtenttermtesttextthanthatthemthentheythinthisthustimetinytiretonetooltosstourtowntreetriptruetubeturntwintypeuglyunituponurgeuseduservaryvastveryviewvotewagewaitwakewalkwallwantwarmwarnwashwaveweakwearweekwellwestwhatwhenwhomwidewifewildwillwindwinewingwipewirewisewishwithwoodwordworkwrapyardyeahyearyellyourzone"
        words[5] = "aboutaboveabuseactoradaptadmitadoptadultafteragainagentagreeaheadalbumaliveallowalonealongalteramongangerangleangryapartappleapplyarguearisearmedasianasideassetavoidawardawareawfulbadlybasicbasisbeachbeginbeingbelowbenchbiblebirthblackbladeblameblindblockbloodboardbrainbrandbreadbreakbrickbriefbringbroadbrownbrushbuildbunchbuyercabincablecarrycatchcausechainchairchartchasecheapcheckcheekchestchiefchildcivilclaimclasscleanclearclimbclockclosecloudcoachcoastcolorcouchcouldcountcourtcovercrackcraftcrashcrazycreamcrimecrosscrowdcycledailydancedeathdelaydepthdirtydoubtdozendraftdramadreamdressdrinkdriveeagerearlyeartheightelecteliteemptyenemyenjoyenterentryequalerroressayeventeveryexactexistextrafaithfalsefaultfavorfencefewerfiberfieldfifthfiftyfightfinalfirstflamefleshfloatfloorfocusforceforthfoundframefreshfrontfruitfullyfunnyghostgiantgivenglassglovegradegraingrandgrantgrassgravegreatgreengroupguardguessguestguidehabithappyheartheavyhellohoneyhonorhorsehotelhousehumanhumoridealimageimplyindexinneriraqiirishissuejointjudgejuiceknifeknocklabellaborlargelaterlatinlaughlayerlearnleastleavelegallemonlevellightlimitlocallooseloverlowerluckylunchmajormakermarrymatchmaybemayormediametalmetermightminormodelmoneymonthmoralmotormountmousemouthmoviemusicnakednervenevernewlynightnoisenorthnovelnurseoccuroceanofferoftenonionorderotheroughtownerpaintpanelpaperpartypatchpausepeacephasephonephotopianopiecepilotpitchplaceplaneplantplatepointporchpoundpowerpresspriceprideprimeprintpriorproofproudprovequickquietquitequoteradioraiserangerapidratioreachreactreadyreferrelaxreplyriflerightriverroughroundrouteruralsaladsalessaucescalescenescopescoreseizesenseservesevenshadeshakeshallshapesharesharpsheetshelfshellshiftshineshirtshockshootshoreshortshoutshrugsightsinceskillslavesleepsliceslidesmallsmartsmellsmilesmokesolarsolidsolvesorrysoundsouthspacespeakspeedspendsplitsportstaffstagestairstakestandstarestartstatestealsteelstickstillstockstonestorestormstorystripstudystuffstylesugarsuperswearsweepsweetswingtabletasteteachtermsthanktheirthemetherethesethickthingthinkthirdthosethreethrowtighttiredtitletodaytoothtopictotaltouchtoughtowertracetracktradetrailtraintreattrendtrialtribetricktrooptrucktrulytrusttruthtwiceuncleunderunionuntilupperurbanusualvaluevideovirusvisitvitalvoicevoterwastewatchwaterweighwheelwherewhichwhilewhitewholewhosewomanworksworldworryworthwouldwoundwritewrongyieldyoungyoursyouth"
        words[6] = "abroadabsorbacceptaccessaccuseacrossactionactiveactualadjustadmireadviceadviseaffairaffectaffordafraidagencyagendaalmostalwaysamountanimalannualansweranyoneanywayappealappeararoundarrestarriveartistasleepaspectassertassessassignassistassumeassureattachattackattendauthorbarelybarrelbasketbattlebeautybecomebeforebehindbeliefbelongbesidebetterbeyondborderborrowbotherbottlebottombranchbreastbreathbridgebrightbrokenbudgetbulletburdenbutterbuttoncameracampuscancercarboncareercenterchancechangechargecheesechoicechoosechurchcircleclientclinicclosercoffeecolumncomedycommitcommoncookiecornercottoncountycouplecoursecousincreatecreditcrisiscriticcustomdamagedangerdealerdebatedecadedecidedeeplydefeatdefenddefinedegreedemanddependdepictdeputyderivedesertdesigndesiredetaildetectdevicedevotedifferdiningdinnerdirectdividedoctordoubledriverduringeasilyeditoreffectefforteither"
        words[7] = "abandonabilityabsenceaccountachieveacquireactressaddressadvanceadviserafricanagainstairlineairportalcoholalreadyamazinganalystanalyzeancientanotheranxietyanybodyanymoreappointapprovearrangearrivalarticleassaultathleteattemptattractaveragebalancebarrierbatterybecausebedroombelievebeneathbenefitbesidesbetweenbillionblanketbombingbreathebrieflybritishbrothercabinetcapablecapitalcaptaincapturecarefulcarrierceilingcentralcenturycertainchamberchannelchaptercharitychickenchinesecitizenclassicclearlyclimatecloselyclothesclustercollectcollegecombinecomfortcommandcommentcompanycomparecompetecomplexcomposeconceptconcernconcertconductconfirmconnectconsistconsumecontactcontaincontentcontestcontextcontrolconvertcookingcorrectcouncilcountercountrycouragecrucialculturecuriouscurrentdeclaredeclinedefensedeficitdeliverdeservedespitedestroydevelopdigitaldiscussdiseasedismissdisplaydisputedistantdiversedivorcedrawingeasterneconomyeditioneducateelderlyelementembraceemotionenglishenhanceepisodeequallyeveningexactlyexamineexampleexhibitexpenseexplainexplodeexploreexpressextremefactoryfacultyfailurefantasyfashionfeaturefederalfeelingfictionfifteenfighterfinallyfinancefindingfishingfitnessforeignforeverformulafortuneforwardfounderfreedomfundingfuneralgallerygeneralgeneticgesturegrocerygrowinghabitathandfulhealthyhearingheavilyhelpfulherselfhighwayhimselfhistoryholidayhorizonhousinghoweverhundredhuntinghusbandillegalillnessimagineimpressimproveincludeinitialinquiryinsightinspireinstallinsteadintenseinvolveislamicisraeliitalianjournaljourneyjusticejustifykillingkitchenlargelylawsuitleadingleatherliberallibrarylicenselimitedmachinemanagermarriedmassivemeaningmeasuremedicalmeetingmentionmessagemexicanmillionmiraclemissilemissionmistakemixturemonitormorningmusicalmysterynaturalneithernervousnetworknothingnowherenuclearobserveobviousoffenseofficerolympicongoingopeningoperateopinionorganicoutcomeoutsideoverallpackagepainfulpainterparkingpartnerpassagepassionpatientpatternpaymentpenaltyperfectperformperhapspictureplasticpopularportionportraypossesspovertypredictpreparepresentpretendpreventprimaryprivacyprivateproblemproceedprocessproduceproductprofileprogramprojectpromisepromoteproposeprotectproteinprotestprovidepublishpurposequalifyqualityquarterquicklyquietlyradicalrapidlyreadingrealityrealizereceiverecoverrecruitreflectrefugeeregularreleasereplacerequestrequireresolverespectrespondrestorerevenueroughlyroutinerunningrussiansatisfyscandalscholarsciencesectionsegmentsenatorseriousservicesessionsettingseveralsheltershortlysilencesimilarsocietysoldiersomehowsomeonespanishspeakerspecialspeciessqueezestationstomachstoragestrangestretchstudentsubjectsucceedsuccesssuggestsuicidesupportsupposesupremesurfacesurgerysurvivesuspectsustainsymptomteachertensiontestifytestingtheatertherapythoughtthroughtobaccotonighttotallytouristtowardstraffictragedytroubletypicalundergouniformunknownunusualusuallyutilityvarietyvariousvehicleventureversionveteranvictoryvillageviolateviolentvisiblevisitorwarningwealthyweatherweddingweekendwelcomewelfarewesternwhereaswhetherwhisperwillingwithoutwitnessworkingworriedwriting"
        words[8] = "abortionabsoluteacademicaccidentaccurateactivistactivityactuallyadditionadequateadvancedadvocateaircraftalliancealthoughamericananalysisannounceanythinganywhereapparentapproachapprovalargumentartisticathleticattitudeattorneyaudiencebaseballbathroombehaviorbirthdayboundarybuildingbusinesscampaigncanadiancapacitycategorycatholicceremonychairmanchampionchangingchemicalcivilianclinicalclothingcollapsecolonialcomplaincompletecomputerconcludeconcreteconflictconfrontcongressconsiderconstantconsumercontinuecontractcontrastconvincecoveragecreationcreativecreaturecriminalcriteriacriticalculturalcustomerdarknessdaughterdecisiondecreasedeliverydemocratdescribedesignerdetaileddialoguedirectlydirectordisagreedisasterdiscoverdisorderdistancedistinctdistrictdivisiondocumentdomesticdominantdominatedowntowndramaticearningseconomiceducatorelectionelectricemissionemphasisemployeeemployerengineerenormousentirelyentranceestimateeuropeanevaluateeverydayeveryoneevidenceexchangeexcitingexerciseexistingexposureexternalfacilityfamiliarfavoritefightingfootballfrequentfriendlyfunctiongenerategovernorgraduategreatestheadlineheritagehistorichomelesshospitalidentifyidentityincidentincreaseindicateindustryinnocentinstanceinterestinternalinternetinvasioninvestorinvolvedjapanesejudgmentlanguagelearninglifetimeliterarylocationmagazinemaintainmajoritymarriagematerialmedicinemilitaryministerminoritymoderatemoreovermortgagemountainmovementmultiplemusiciannationalnegativeneighbornormallynorthernnumerousobserveroccasionofficialoperatoropponentoppositeordinaryorganizeoriginalovercomeoverlookpaintingperceivepersonalpersuadephysicalplanningplatformpleasurepoliticsportraitpositionpositivepossiblepossiblypowerfulpracticepregnantpresencepreservepressurepreviouspriorityprisonerprobablyproducerprogressproperlypropertyproposalproposedprospectproviderprovincepubliclypurchasequestionreactionrecentlyrecoveryregionalregisterregulaterelationrelativerelevantreligionrememberreporterresearchresembleresidentresourceresponseromanticsanctionscenarioschedulesecuritysentenceseparatesequenceshootingshoppingshoulderslightlysoftwaresolutionsomebodysomewhatsouthernspecificspendingstandardstandingstraightstrangerstrategystrengthstronglystrugglesuddenlysupposedsurprisesurroundsurvivalsurvivortaxpayerteachingteaspoonteenagertendencyterriblethinkingthousandthreatentogethertomorrowtrainingtransferultimateuniverseunlikelyvacationvaluablevariableviolencewhateverwheneverwithdrawworkshopyourself"
    }


}
