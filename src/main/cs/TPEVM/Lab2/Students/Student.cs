using System.Text;

namespace Students

{
    public class Student
    {
        private static uint AutoId = 0;

        public uint Id { get; } = AutoId++;
        public string LastName { set; get; } = string.Empty;
        public string FirstName { set; get; } = string.Empty;
        public string SecondName { set; get; } = string.Empty;
        public string Adress { set; get; } = string.Empty;
        public DateOnly BirthDate { set; get; } = new();
        public string PhoneNumber { set; get; } = string.Empty;

        public Student() {}

        public Student(string LastName, string FirstName, string SecondName, string Adress, DateOnly BirthDate, string PhoneNumber) 
        {
            this.LastName = LastName;
            this.FirstName = FirstName;
            this.SecondName = SecondName;
            this.Adress = Adress;
            this.BirthDate = BirthDate;
            this.PhoneNumber = PhoneNumber;
        }

        public override string ToString()
        {
            StringBuilder stringBuilder = new();
            stringBuilder.Append("ID: ");
            stringBuilder.Append(Id);
            stringBuilder.Append(", Фамилия: ");
            stringBuilder.Append(LastName);
            stringBuilder.Append(", Имя: ");
            stringBuilder.Append(FirstName);
            stringBuilder.Append(", Отчество: ");
            stringBuilder.Append(!SecondName.Equals(string.Empty) ? SecondName : '-');
            stringBuilder.Append(", Адрес: ");
            stringBuilder.Append(Adress);
            stringBuilder.Append(", Дата рождения: ");
            stringBuilder.Append(BirthDate.ToLongDateString());
            stringBuilder.Append(", Телефон: ");
            stringBuilder.Append(PhoneNumber);
            return stringBuilder.ToString();
        }

        public override bool Equals(object? obj)
        {
            if (obj == null || obj is not Student) return false;
            Student student = (Student) obj;
            return this.Id == student.Id &&
                    this.SecondName.Equals(student.SecondName) &&
                    this.FirstName.Equals(student.FirstName) && 
                    this.LastName.Equals(student.LastName) && 
                    this.Adress.Equals(student.Adress) &&
                    this.BirthDate.Equals(student.BirthDate) && 
                    this.PhoneNumber.Equals(student.PhoneNumber);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Id, LastName, SecondName, FirstName, Adress, BirthDate, PhoneNumber);
        }
    }
}
