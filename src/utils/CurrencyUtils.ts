class CurrencyUtils {
  static formatToVND(amount: number) {
    return Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(amount);
  }
}
export default CurrencyUtils;
