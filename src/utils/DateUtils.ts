class DateUtils {
  static getFormattedDate(date: Date) {
    const days = [
      "Sunday",
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
    ];
    const months = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ];
    const dayName = days[date.getDay()]; //get ten ngay
    const day = date.getDate(); //get ngay
    const month = months[date.getMonth()]; //get thang
    const year = date.getFullYear(); //get nam

    return `${dayName}, ${day},${month},${year}`;
  }
  static formatDateString(dateString: String) {
    if (dateString != undefined) {
      const date = new Date(dateString);
      return Intl.DateTimeFormat("en-US", {
        year: "numeric",
        month: "long",
        day: "2-digit",
      }).format(date);
    }
  }
}
export default DateUtils;
