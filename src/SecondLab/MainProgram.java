package SecondLab;

// 000010011
// 1 - В массиве число элементов меньше указанного
// 2 - В строке отсутствует какой-то символ
// 5 - Меньше, чем некоторое число

class Process  implements IFuncs, IConst
{
    @Override
    public int sum(String[] args) {

        int sum1 = 0;
        int sum2 = 0;

        try {
            for (String number : args) {
                int num = Integer.parseInt(number);
                checkNumber(num);

                if (num >= 0) {
                    if (num % 2 == 1) {
                        sum1 += num;
                    } else {
                        sum2 += num;
                    }
                }
            }
            return sum1 + sum2;
        } catch (NumberLessConst e) {
            System.out.println (e);
            return -3;
        }
    }

    @Override
    public void checkNumber(int number) throws NumberLessConst {
        if(number < special_number)
            throw new NumberLessConst();
    }

    @Override
    public void findSymbol(String[] args) throws MinusNotFound {
        boolean isSucces = false;
        for (String number : args)
        {
            if (number.contains(special_symbol))
            {
                isSucces = true; break;
            }
        }
        if (!isSucces)
        {
            throw new MinusNotFound();
        }
    }

    @Override
    public void checkSize(String[] args) throws ArrayIsTooSmall {
        if(args.length < min_array_size)
            throw new ArrayIsTooSmall();
    }
}

public class MainProgram
{
   private final static Process process = new Process();

   public static int process_sum(String[] args)
   {
       int result;

       try {
           process.checkSize(args);
           process.findSymbol(args);

           result = process.sum(args);

       } catch (ArrayIsTooSmall e) {
           System.out.println (e);
           result = -1;
       } catch (MinusNotFound e) {
           System.out.println (e);
           result = -2;
       }

       return result;
   }
}
