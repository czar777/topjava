package ru.javawebinar.topjava.service;

import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"jdbc", "hsqldb"})
@Ignore
public class JDBCMealServiceTest extends PMealServiceTest{

}
