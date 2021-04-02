package data_structures;

import java.io.Serializable;
import java.net.InetAddress;

public class InetAddressAndPort implements Serializable {
    public final InetAddress address;
    public final int port;

    public InetAddressAndPort(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddressAndPort withPort(int port)
    {
        return new InetAddressAndPort(address, port);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InetAddressAndPort that = (InetAddressAndPort) o;

        if (port != that.port) return false;
        return address.equals(that.address);
    }

    public String getHostAddressAndPort()
    {
        return getHostAddress(true);
    }

    public String getHostAddress(boolean withPort)
    {
        if (withPort)
        {
            return toString(this.address, this.port);
        }
        else
        {
            return address.getHostAddress();
        }
    }

    @Override
    public String toString()
    {
        return toString(true);
    }

    public String toString(boolean withPort)
    {
        if (withPort)
        {
            return toString(this.address, this.port);
        }
        else
        {
            return address.toString();
        }
    }

    public static String toString(InetAddress address, int port)
    {
        String addressToString = address.toString(); // cannot use getHostName as it resolves
        int nameLength = addressToString.lastIndexOf('/'); // use last index to prevent ambiguity if host name contains /
        assert nameLength >= 0 : "InetAddress.toString format may have changed, expecting /";

        // Check if need to wrap address with [ ] for IPv6 addresses
        if (addressToString.indexOf(':', nameLength) >= 0)
        {
            StringBuilder sb = new StringBuilder(addressToString.length() + 16);
            sb.append(addressToString, 0, nameLength + 1); // append optional host and / char
            sb.append('[');
            sb.append(addressToString, nameLength + 1, addressToString.length());
            sb.append("]:");
            sb.append(port);
            return sb.toString();
        }
        else // can just append :port
        {
            StringBuilder sb = new StringBuilder(addressToString); // will have enough capacity for port
            sb.append(":");
            sb.append(port);
            return sb.toString();
        }
    }


}
