export module employee;
import <iostream>;
import <string>;
import <format>;
export struct Employee
{
	std::string firstName;
	std::string lastName;
	int employeeNumber;
	double salary;

	void outputInfo()
	{
		std::cout << std::format("{0} {1}\n", firstName, lastName);
		std::cout << std::format("{0} {1:10.2f}\n", employeeNumber, salary);
	}
};