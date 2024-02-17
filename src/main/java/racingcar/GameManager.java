package racingcar;

import racingcar.domain.Car;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {

    private static final int MAX_RANDOM_NUMBER = 9;
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void run() {
        outputView.printCarNameInputMessage();
        List<String> carNames = inputView.getCarName();
        List<Car> cars = makeNewCars(carNames);
        outputView.printTryCountInputMessage();
        int tryCount = inputView.getTryCount();
        for (int i = 0; i < tryCount; i++) {
            moveCars(cars);
            outputView.printTryResult(cars);
        }
        outputView.printWinners(cars);
    }

    private void moveCars(List<Car> cars) {
        for (Car car : cars) {
            car.moveCar(ThreadLocalRandom.current().nextInt(MAX_RANDOM_NUMBER + 1));
        }
    }

    private List<Car> makeNewCars(List<String> carNames) {
        return carNames.stream()
                .map(carName -> new Car(carName))
                .toList();
    }
}
