import apiClient from "../config/api-client";
import { Expense } from "../model/Expense";

export const getExpenses = () => {
  return apiClient.get<Expense>("/expenses");
};

export const getExpensesByExpenseId = (expenseId: string) => {
  return apiClient.get<Expense>(`/expenses/${expenseId}`);
};

export const deleteExpenseByExpenseId = (expenseId: string) => {
  return apiClient.delete<void>(`/expenses/${expenseId}`);
};

export const saveOrUpdateExpense = (Expense) => {
  return apiClient.post<Expense>(`/expenses`, Expense);
};
