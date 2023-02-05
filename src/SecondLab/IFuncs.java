package SecondLab;

public interface IFuncs {
    int sum(String[] args);
    void checkNumber(int number) throws NumberLessConst;
    void findSymbol(String[] args) throws MinusNotFound;
    void checkSize(String[] args) throws ArrayIsTooSmall;

}
