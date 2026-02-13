
 
        class Calculator {
            constructor(previousDisplayElement, currentDisplayElement) {
                this.previousDisplayElement = previousDisplayElement;
                this.currentDisplayElement = currentDisplayElement;
                this.clear();
            }

            clear() {
                this.currentOperand = '0';
                this.previousOperand = '';
                this.operation = undefined;
            }

            delete() {
                if (this.currentOperand === '0') return;
                this.currentOperand = this.currentOperand.toString().slice(0, -1);
                if (this.currentOperand === '') {
                    this.currentOperand = '0';
                }
            }

            appendNumber(number) {
                if (number === '.' && this.currentOperand.includes('.')) return;
                if (number === '%') {
                    this.currentOperand = (parseFloat(this.currentOperand) / 100).toString();
                    this.updateDisplay();
                    return;
                }
                if (this.currentOperand === '0' && number !== '.') {
                    this.currentOperand = number.toString();
                } else {
                    this.currentOperand = this.currentOperand.toString() + number.toString();
                }
                this.updateDisplay();
            }

            chooseOperation(operation) {
                if (this.currentOperand === '') return;
                if (this.previousOperand !== '') {
                    this.compute();
                }
                this.operation = operation;
                this.previousOperand = this.currentOperand;
                this.currentOperand = '0';
            }

            compute() {
                let computation;
                const prev = parseFloat(this.previousOperand);
                const current = parseFloat(this.currentOperand);
                if (isNaN(prev) || isNaN(current)) return;
                
                switch (this.operation) {
                    case '+':
                        computation = prev + current;
                        break;
                    case '-':
                        computation = prev - current;
                        break;
                    case '×':
                        computation = prev * current;
                        break;
                    case '÷':
                        if (current === 0) {
                            alert('Cannot divide by zero!');
                            this.clear();
                            this.updateDisplay();
                            return;
                        }
                        computation = prev / current;
                        break;
                    default:
                        return;
                }
                
                this.currentOperand = computation.toString();
                this.operation = undefined;
                this.previousOperand = '';
            }

            getDisplayNumber(number) {
                const stringNumber = number.toString();
                const integerDigits = parseFloat(stringNumber.split('.')[0]);
                const decimalDigits = stringNumber.split('.')[1];
                let integerDisplay;
                
                if (isNaN(integerDigits)) {
                    integerDisplay = '';
                } else {
                    integerDisplay = integerDigits.toLocaleString('en', {
                        maximumFractionDigits: 0
                    });
                }
                
                if (decimalDigits != null) {
                    return `${integerDisplay}.${decimalDigits}`;
                } else {
                    return integerDisplay;
                }
            }

            updateDisplay() {
                this.currentDisplayElement.textContent = this.getDisplayNumber(this.currentOperand);
                if (this.operation != null) {
                    this.previousDisplayElement.textContent = 
                        `${this.getDisplayNumber(this.previousOperand)} ${this.operation}`;
                } else {
                    this.previousDisplayElement.textContent = '';
                }
            }
        }

        const previousDisplayElement = document.getElementById('previousDisplay');
        const currentDisplayElement = document.getElementById('currentDisplay');

        const calculator = new Calculator(previousDisplayElement, currentDisplayElement);

        // Keyboard support
        document.addEventListener('keydown', function(event) {
            if (event.key >= 0 && event.key <= 9) {
                calculator.appendNumber(event.key);
            }
            if (event.key === '.') {
                calculator.appendNumber('.');
            }
            if (event.key === '+' || event.key === '-') {
                calculator.chooseOperation(event.key);
            }
            if (event.key === '*') {
                calculator.chooseOperation('×');
            }
            if (event.key === '/') {
                event.preventDefault();
                calculator.chooseOperation('÷');
            }
            if (event.key === 'Enter' || event.key === '=') {
                calculator.compute();
            }
            if (event.key === 'Backspace') {
                calculator.delete();
            }
            if (event.key === 'Escape') {
                calculator.clear();
            }
            calculator.updateDisplay();
        });
