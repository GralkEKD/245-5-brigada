using System.Text;

namespace Students
{
    public class Group
    {
        public string Number {get; set;} = string.Empty;
        public Dictionary<uint, Student> Students = [];

        public Group() {}

        public Group(string Number)
        {
            this.Number = Number;
        }

        internal Group(string Number, Student[] Students)
        {
            this.Number = Number;
            foreach (var student in Students)
            {
                this.Students.Add(student.Id, student);
            }
        }

        public bool Add(Student student)
        {
            return Students.TryAdd(student.Id, student);
        }

        public bool Remove(uint Id)
        {
            return Students.Remove(Id);
        }

        public Student this[uint Id]
        {
            get => Students[Id];
        }

        public string GetInfo()
        {
            StringBuilder stringBuilder = new();
            List<Student> studentValues = [.. Students.Values];
            studentValues.Sort(
                (x1, x2) => x1.LastName.CompareTo(x2.LastName)
            );
            foreach (var student in studentValues)
            {
                stringBuilder.Append('\t');
                stringBuilder.Append(student.ToString());
                stringBuilder.Append('\n');
            }
            return stringBuilder.ToString();
        }
    }
}