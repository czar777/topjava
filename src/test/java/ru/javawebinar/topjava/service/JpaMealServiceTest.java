package ru.javawebinar.topjava.service;

import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;

//@IfProfileValue(name = "spring.profiles.active", values = {"jpa", "hsqldb"})
@ActiveProfiles({"jpa", "hsqldb"})
public class JpaMealServiceTest extends PMealServiceTest {
}
