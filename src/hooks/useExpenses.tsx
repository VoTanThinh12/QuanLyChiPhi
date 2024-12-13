import { useState, useEffect } from "react";
import { getExpenses } from "../services/expense-service";
import { Expense } from "../model/Expense";

const useExpenses = (expenseId: string) => {
  const [expenses, setExpenses] = useState<Expense[]>([]);
  const [error, setErrors] = useState<string>("");
  const [isLoading, setLoader] = useState<boolean>(false) ;

  useEffect(() => {
    setLoader(true);
    getExpenses()
      .then((response) => {
        setExpenses(response.data);
        setLoader(false);
      })
      .catch((error) => setErrors(error.message))
      .finally(() => setLoader(false));
  }, []);
  return { expenses, error, isLoading };
};
export default useExpenses;
