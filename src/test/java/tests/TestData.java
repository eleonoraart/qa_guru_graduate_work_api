package tests;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();

    public final static String

            testCaseName = faker.chuckNorris().fact(),
            nameStepTestCase = faker.gameOfThrones().dragon();

}
