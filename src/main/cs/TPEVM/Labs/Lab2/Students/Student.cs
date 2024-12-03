using System.Text;

namespace Students

{
    public class Student
    {
        private static uint _autoId;

        public uint Id { get; } = _autoId++;
        public string LastName { set; get; } = string.Empty;
        public string FirstName { set; get; } = string.Empty;
        public string SecondName { set; get; } = string.Empty;
        public string Address { set; get; } = string.Empty;
        public DateOnly BirthDate { set; get; }
        public string PhoneNumber { set; get; } = string.Empty;

        public Student() {}

        public Student(string lastName, string firstName, string secondName, string address, DateOnly birthDate, string phoneNumber) 
        {
            this.LastName = lastName;
            this.FirstName = firstName;
            this.SecondName = secondName;
            this.Address = address;
            this.BirthDate = birthDate;
            this.PhoneNumber = phoneNumber;
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
            stringBuilder.Append(Address);
            stringBuilder.Append(", Дата рождения: ");
            stringBuilder.Append(BirthDate.ToLongDateString());
            stringBuilder.Append(", Телефон: ");
            stringBuilder.Append(PhoneNumber);
            return stringBuilder.ToString();
        }

        public override bool Equals(object? obj)
        {
            if (obj is not Student student) return false;
            return this.SecondName.Equals(student.SecondName) &&
                   this.FirstName.Equals(student.FirstName) && 
                   this.LastName.Equals(student.LastName) && 
                   this.Address.Equals(student.Address) &&
                   this.BirthDate.Equals(student.BirthDate) && 
                   this.PhoneNumber.Equals(student.PhoneNumber);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Id, LastName, SecondName, FirstName, Address, BirthDate, PhoneNumber);
        }
    }
}
