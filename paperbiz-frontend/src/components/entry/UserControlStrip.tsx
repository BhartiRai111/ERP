/** Strip under the form: caption + value pairs such as "Prepared By", "Modified By", "System Generated". */
export function UserControlStrip({ blogs }: { blogs: { caption: string; value: string }[] }) {
  return (
    <div className="mt-auto grid grid-cols-1 gap-2 border-t pt-2 text-xs sm:grid-cols-3" data-testid="user-control-strip">
      {blogs.map((b) => (
        <div key={b.caption} className="flex flex-col">
          <span className="text-muted-foreground">{b.caption}</span>
          <span>{b.value || " "}</span>
        </div>
      ))}
    </div>
  );
}
