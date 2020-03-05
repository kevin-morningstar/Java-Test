package testcases;

class normal {

	public static void main(String[] args) {
		String a = "₹16510";
		System.out.println(a.substring(1));
		int b = Integer.parseInt((a.substring(1)));
		System.out.println(b);

}
}