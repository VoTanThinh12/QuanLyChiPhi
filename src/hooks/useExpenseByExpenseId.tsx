import { useEffect, useState } from "react";
import { Expense } from "../model/Expense";
import { getExpensesByExpenseId } from "../services/expense-service";

const useExpenseByExpenseId = (expenseId: string) => {
  const [expenses, setExpense] = useState<Expense | undefined>();
  const [errors, setErrors] = useState<string>("");
  const [isLoading, setLoader] = useState<boolean>(false);

  useEffect(() => {
    setLoader(true);
    getExpensesByExpenseId(expenseId)
      .then((response) => setExpense(response.data))
      .catch((error) => {
        setErrors(error.message);
        console.log(error);
      })
      .finally(() => setLoader(false));
  }, []);
  return { expenses, errors, isLoading, setLoader, setErrors };
};

export default useExpenseByExpenseId;
