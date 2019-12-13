import java.util.Arrays;
import java.util.List;

public class Program {

	private static int count = 0;

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 12, 8, 9, 6, 7, 11, 14, 13, 15);
		Tree<Integer> tree = new Tree<>();
		for (Integer integer : list) {
			tree.add(integer);
		}
		tree.remove(9);
		tree.remove(10);
		tree.remove(13);
		tree.traverse(TraverseOrder.PREFIX, (val) -> {
			count += val;
			System.out.println(val.toString() + " ");
		});
		System.out.println("Sum = " + count);
	}

}
