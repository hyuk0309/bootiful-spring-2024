package org.example.bootifulspring2024;

// more safer and more faster.

class Loans {
	String displayMessageFor(Loan loan) {
		// switch has better performance than if statement
		var msg = switch (loan) {
			case UnsecuredLoan(var interest) -> "ouch! that " + interest + "% rate hurts.";
			case SecuredLoan sl -> "good job. well done. you got a loan.";
		};
		return msg;
	}
}

sealed interface Loan permits SecuredLoan, UnsecuredLoan {
}

final class SecuredLoan implements Loan {}

// toString, equals, hashCode, constructors, storage in the class,
// and accessor methods
record UnsecuredLoan(float interest) implements Loan {}