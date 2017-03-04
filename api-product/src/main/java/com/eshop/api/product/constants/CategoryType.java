package com.eshop.api.product.constants;

public enum CategoryType {
	
	MenS(0, "男装"),Womens(1,"女装"),underwear(2,"内衣"),Shoes(3,"鞋靴"),Luggage(4,"箱包"),Accessories(5,"配件"),ChildrenS(6,"童装玩具"),
	Maternity(7,"孕产"),Dairy(8,"奶食"),HomeAppliances(9,"家电"),Digital(10,"数码"),Mobile(11,"手机"),makeups(12,"美妆"),Care(13,"洗护"),
	HealthCareProducts(14,"保健品"),Jewelry(15,"珠宝"),Glasses(16,"眼镜"),Watch(17,"手表"),movement(18,"运动"),outdoor(19,"户外健身"),
	MusicalInstruments(20,"乐器"),Game(21,"游戏"),Anime(22,"动漫"),Film(23,"影视"),Food(24,"美食"),Fresh(25,"生鲜"),Snacks(26,"零食"),
	Flowers(27,"鲜花"),pet(28,"宠物"),AgriculturalResources(29,"农资"),Property(30,"房产"),Decoration(31,"装修"),BuildingMaterials(32,"建材"),
	Furniture(33,"家具"),Furnishings(34,"家饰"),HomeTextiles(35,"家纺"),Car(36,"汽车"),Second_handcar(37,"二手车"),AutoAccessories(38,"汽车用品"),
	Office(39,"办公"),DIY(40,"DIY"),Hardware(41,"五金电子"),DepartmentStore(42,"百货"),tableware(43,"餐具"),FamilyHealthCare(44,"家庭保健"),
	Learn(45,"学习"),Card(46,"卡卷"),LocalService(47,"本地服务");
	
    private String name;
	private int code;

	private CategoryType( int code,String name) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

}
