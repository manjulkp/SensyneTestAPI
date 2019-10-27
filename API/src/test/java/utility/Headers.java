package utility;

import io.restassured.internal.MultiValueEntity;

import java.util.*;

import static io.restassured.internal.common.assertion.AssertParameter.notNull;

/**
 * Represents collection of headers
 */
public class Headers implements Iterable<Header> {

    private final MultiValueEntity<Header> headers;

    public Headers(Header... headers) {
        this(Arrays.asList(headers));
    }

    public Headers(List<Header> headers) {
        notNull(headers, "Headers");
        this.headers = new MultiValueEntity<>(headers);
    }

    /**
     * @return The size of the headers
     */
    public int size() {
        return headers.size();
    }

    /**
     * @return <code>true</code> if one or more headers are defined, <code>false</code> otherwise.
     */
    public boolean exist() {
        return headers.exist();
    }

    /**
     * See if a header with the given name exists
     *
     * @param headerName The name of the header to check
     * @return <code>true</code> if the header exists
     */
    public boolean hasHeaderWithName(String headerName) {
        return headers.hasEntityWithName(headerName);
    }

    /**
     * @return All headers as a list.
     */
    protected List<Header> list() {
        return headers.list();
    }

    /**
     * Get a single header with the supplied name. If there are several headers match the <code>headerName</code> then
     * the last one is returned.
     *
     * @param headerName The name of the header to find
     * @return The found header or <code>null</code> if no header was found.
     */
    public Header get(String headerName) {
        notNull(headerName, "Header name");
        return headers.get(headerName);
    }

    /**
     * Get a single header with the supplied name. If there are several headers match the <code>headerName</code> then
     * the last one is returned.
     *
     * 
     */
    public String getValue(String headerName) {
        return headers.getValue(headerName);
    }

    /**
     * Get all header values of the header with supplied name. If there's only one header matching the <code>headerName</code> then
     * a list with only that header value is returned.
     *
       */
    public List<String> getValues(String headerName) {
        return headers.getValues(headerName);
    }

    /**
     * Get all headers with the supplied name. If there's only one header matching the <code>headerName</code> then
     * a list with only that header is returned.
     *
     * @param headerName The name of the header to find
     * @return The found headers or empty list if no header was found.
     */
    public List<Header> getList(String headerName) {
        return headers.getList(headerName);
    }

    /**
     * @return Headers iterator
     */
    public Iterator<Header> iterator() {
        return headers.iterator();
    }

    /**
     * @return All headers as a list
     */
    public List<Header> asList() {
        return headers.asList();
    }

    /**
     * An alternative way to create a Headers object from the constructor.
     *
     * @param header            The header to be included
     * @param additionalHeaders Additional headers to be included (optional)
     * @return A new headers object containing the specified headers
     */
    public static Headers headers(Header header, Header... additionalHeaders) {
        notNull(header, "Header");
        final List<Header> headerList = new LinkedList<Header>();
        headerList.add(header);
        if (additionalHeaders != null) {
            Collections.addAll(headerList, additionalHeaders);
        }
        return new Headers(headerList);
    }

    @Override
    public String toString() {
        return headers.toString();
    }
}