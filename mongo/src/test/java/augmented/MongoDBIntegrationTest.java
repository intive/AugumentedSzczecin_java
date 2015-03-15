package augmented;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MongoDBIntegrationTest {

    // FIXME rewrite based on guice test example
/*

    private static MongodForTestsFactory factory;
    private static Datastore datastore;
    private static CommonDao poiDao;


    private static interface TestInfo {
        String COLLECTION_NAME = "testingCollection";

        String NAME = "TestPoi1";
        Tag TAG = Tag.BAR;
    }

    @BeforeClass
    public static void setup() throws Exception {
        factory = MongodForTestsFactory.with(Version.Main.PRODUCTION);
        datastore = new Morphia().createDatastore(factory.newMongo(), TestInfo.COLLECTION_NAME);
        poiDao = new CommonDaoMongo(datastore);
    }

    @AfterClass
    public static void teardown() throws Exception {
        if (factory != null)
            factory.shutdown();
    }

    @Test
    public void testSave() {
        final Poi testPoi = createPoi(TestInfo.NAME, TestInfo.TAG, new Location(124141l, 5214314l));
        final String savedPoiId = poiDao.savePoi(testPoi);
        final Poi savedPoi = datastore.get(PoiMongo.class, new ObjectId(savedPoiId));
        checkEqualityOfPois(savedPoi, testPoi);
    }

    @Test
    public void testFindAll() {
        assertEquals(poiDao.findAllPoi().size(), datastore.find(PoiMongo.class).asList().size());
    }

    @Test
    public void testGet() {
        final PoiMongo toCreatePoi = (PoiMongo) createPoi(TestInfo.NAME, Tag.BAR, new Location(1321l, 51433l));
        final String savedPoiId = datastore.<PoiMongo>save(toCreatePoi).getId().toString();
        final PoiMongo savedPoi = datastore.get(PoiMongo.class, new ObjectId(savedPoiId));
        final Poi getSavedPoi = poiDao.findById(savedPoiId);
        checkEqualityOfPois(getSavedPoi, savedPoi);
    }

    @Test
    public void testFindbyTag() {
        final PoiMongo toCreatePoi = (PoiMongo) createPoi(TestInfo.NAME, Tag.BUS_STOP, new Location(1321l, 51433l));
        final String savedPoiId = datastore.<PoiMongo>save(toCreatePoi).getId().toString();
        final List<Poi> findTestPoi = poiDao.findByTag(Tag.BUS_STOP);
        assertTrue(!findTestPoi.isEmpty());
        assertTrue(findTestPoi.stream().anyMatch(new Predicate<Poi>() {
            @Override
            public boolean test(Poi poi) {
                return poi.getId().equals(savedPoiId);
            }
        }));
    }

    private void checkEqualityOfPois(Poi getTestPoi, Poi testPoi) {
        assertEquals(getTestPoi.getName(), testPoi.getName());
        assertEquals(getTestPoi.getTag(), testPoi.getTag());
        final Location getTestPoiLocation = getTestPoi.getLocation();
        final Location testPoiLocation = testPoi.getLocation();
        if (getTestPoiLocation != null && testPoiLocation != null) {
            assertEquals(getTestPoiLocation.getLongitude(), testPoiLocation.getLongitude());
            assertEquals(getTestPoiLocation.getLatitude(), testPoiLocation.getLatitude());
        }

    }


    private Poi createPoi(final String name, final com.bls.core.Tag tag, final Location location) {
        Poi poi = new PoiMongo();
        poi.setName(name);
        poi.setTag(tag);
        poi.setLocation(location);
        return poi;
    }
*/
}
