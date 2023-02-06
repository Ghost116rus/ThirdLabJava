package SecondLab;

// 000010011
// 1 - В массиве число элементов меньше указанного
// 2 - В строке отсутствует какой-то символ
// 5 - Меньше, чем некоторое число

import ThirdLab.Watcher;

import java.util.Observable;

class Process extends Observable implements IFuncs, IConst
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
            notifyObs(e);
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
        notifyObs("Обращаемся к массиву для проверки на наличие в нем специального символа");
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
        notifyObs("Обращаемся к массиву для проверки его размера");
        if(args.length < min_array_size)
            throw new ArrayIsTooSmall();
    }

    private void notifyObs(Object obj) {
        setChanged();
        notifyObservers(obj);
    }
}

public class MainProgram extends Observable
{
   private final Process process;

   public MainProgram(Watcher w)
   {
       process = new Process();
       process.addObserver(w);
       this.addObserver(w);
   }
   public int process_sum(String[] args)
   {
       int result;

       try {
           process.checkSize(args);
           process.findSymbol(args);

           notifyObs("Обращаемся к массиву для подсчитывания суммы и проверки его членов");
           result = process.sum(args);

       } catch (ArrayIsTooSmall e) {
           notifyObs(e);
           result = -1;
       } catch (MinusNotFound e) {
           notifyObs(e);
           result = -2;
       }

       return result;
   }

    private void notifyObs(Object obj) {
        setChanged();
        notifyObservers(obj);
    }
}
