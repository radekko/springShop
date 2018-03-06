package test.springshop;

public class SpittleControllerTest {

//	@Test
//	public void shouldShowRecentSpittles()throws Exception{
//		List<Spittle> expectedSplittles = createSpittleList();
//		SpittleRepository mockRepository = mock(SpittleRepository.class);
//		when(mockRepository.findSpittles(Integer.MAX_VALUE, 5)).thenReturn(expectedSplittles);		
//		
//		SpittleController spittleController = new SpittleController(mockRepository);
//		MockMvc mock = standaloneSetup(spittleController)
//				.setSingleView(new InternalResourceView("WEB_INF/view/spittles.jsp")).build();
//		mock.perform(get("/spittle"))
//			.andExpect(view().name("spittles"))
//			.andExpect(model().attributeExists("spittleList"));
//		System.out.println("aaa");
//	}
//	
//	public List<Spittle> createSpittleList(){
//		List<Spittle> spittles = new ArrayList<Spittle>();
//        for (int i = 0; i < 5; i++) {
//            spittles.add(new Spittle( i, new Date().toString()));
//        }
//		return spittles;
//	}
}
